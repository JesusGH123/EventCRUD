package com.example.ProyectoFinal.controller;

import com.example.ProyectoFinal.dao.EventDao;
import com.example.ProyectoFinal.model.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "event", value = "/event")
@MultipartConfig
public class EventController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("doGet event");
        if(session.getAttribute("username")!=null){
            System.out.println("user is logged in");
            //List<Event> events = eventDao
            EventDao event_dao = new EventDao();
            List<Event> events = event_dao.getEvents();
            request.setAttribute("events",events);
            request.getRequestDispatcher("home.jsp").forward(request,response);
        }else
            request.getRequestDispatcher("login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EventDao event_dao = new EventDao();
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("type") !=null && request.getParameter("type").equals("single")){
            int event_id =  request.getParameter("event_id") == null ? 0 : Integer.parseInt(request.getParameter("event_id"));
            Event event = event_dao.getEvent(event_id);
            Gson gson = new GsonBuilder().create();
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(gson.toJson(event));
        }else{
            Event event = new Event();
            event.setTitle(request.getParameter("eventName"));
            event.setDescription(request.getParameter("eventDescription"));
            event.setCategory(request.getParameter("eventCategory"));
            event.setBegin_date(Timestamp.valueOf(request.getParameter("eventStartDay") + " " + request.getParameter("eventStartHour") + ":00"));
            event.setEnd_date(Timestamp.valueOf(request.getParameter("eventEndDay") + " " + request.getParameter("eventEndHour") + ":00"));
            event.setPrice(Float.parseFloat(request.getParameter("eventPrice")));
            int attendance_limit = request.getParameter("attendance_limit") == null ? 0 : Integer.parseInt(request.getParameter("attendance_limit")); //missing in form
            event.setAttendance_limit(attendance_limit);
            ///create event
            Part part = request.getPart("file");
            if (part != null) {
                Event.EventImage event_image = new Event.EventImage();
                event_image.setName(part.getSubmittedFileName());
                event_image.setType(part.getContentType());
                event_image.setSize(part.getSize());
                event_image.setContent(part.getInputStream());
                event.setImage(event_image);
            }
            //save event
            Event event_with_ids = event_dao.saveEvent(event);
            Gson gson = new Gson();
            String message = "";
            if (event_with_ids.getEvent_id() > 0) {
                //Correctly
                message = gson.toJson(event_with_ids);
            } else {
                // Incorrectly Inserted and does not have id
                message = "{\"event_id\":0,\"message\": \"El evento no se puedo insertar\"}";
            }
            //response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(message);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.doPut(req, resp);
        try {
            EventDao event_dao = new EventDao();
            System.out.println("do put");
            request.setCharacterEncoding("UTF-8");
            Event event = new Event();
            event.setEvent_id(Integer.parseInt(request.getParameter("event_id")));
            event.setTitle(request.getParameter("eventName"));
            event.setDescription(request.getParameter("eventDescription"));
            event.setCategory(request.getParameter("eventCategory"));
            event.setBegin_date(Timestamp.valueOf(request.getParameter("eventStartDay") + " " + request.getParameter("eventStartHour") + (request.getParameter("eventStartHour").length() <=5 ? ":00" :"")));
            event.setEnd_date(Timestamp.valueOf(request.getParameter("eventEndDay") + " " + request.getParameter("eventEndHour") + (request.getParameter("eventEndHour").length() <=5 ? ":00":"" )));
            event.setPrice(Float.parseFloat(request.getParameter("eventPrice")));
            int attendance_limit = request.getParameter("attendance_limit") == null ? 0 : Integer.parseInt(request.getParameter("attendance_limit")); //missing in form
            event.setAttendance_limit(attendance_limit);
            ///create event
            Part part = request.getPart("file");
            if (part != null) {
                Event.EventImage event_image = new Event.EventImage();
                event_image.setName(part.getSubmittedFileName());
                event_image.setType(part.getContentType());
                event_image.setSize(part.getSize());
                event_image.setContent(part.getInputStream());
                event.setImage(event_image);
            }
            //save event
            boolean was_successfully_updated = event_dao.updateEvent(event);
            Gson gson = new Gson();
            String message = "";
            if (was_successfully_updated) {
                //Correctly
                message = "{\"event_id\":0,\"message\": \"El registro fue actualizado exitosamente\"}";
            } else {
                // Incorrectly Inserted and does not have id
                message = "{\"event_id\":0,\"message\": \"El registro no se pudo actualizar\"}";
            }
            //response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);
        int event_id = req.getParameter("event_id") == null || req.getParameter("event_id") == "" ? 0 : Integer.parseInt(req.getParameter("event_id"));
        System.out.println(event_id);
        EventDao event_dao = new EventDao();
        boolean wasDeleted = event_dao.deleteEvent(event_id);
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        String message = wasDeleted ? "{\"message\": \"Borrar exitoso\"}" : "{\"message\": \"Borrar falló\"}";
        out.print(message);
    }
}
