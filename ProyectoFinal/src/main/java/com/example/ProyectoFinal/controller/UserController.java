package com.example.ProyectoFinal.controller;

import com.example.ProyectoFinal.dao.SaveUserResult;
import com.example.ProyectoFinal.dao.UserAndResult;
import com.example.ProyectoFinal.dao.UserDao;
import com.example.ProyectoFinal.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "user", value = "/user")
@MultipartConfig
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession();

        if(session.getAttribute("isAdmin")==null || !(boolean)request.getSession().getAttribute("isAdmin"))
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);

        if(session.getAttribute("username") != null) {
            request.setAttribute("isAdmin",session.getAttribute("isAdmin"));
            request.setAttribute("username",session.getAttribute("username"));
            UserDao userDao = new UserDao();
            List<User> users = userDao.getUsers();
            request.setAttribute("users", users);

            request.getRequestDispatcher("WEB-INF/users.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        System.out.println("doPost of user");
        if(request.getSession().getAttribute("isAdmin")==null || !(boolean)request.getSession().getAttribute("isAdmin"))
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        UserDao user_dao = new UserDao();
        if(request.getParameter("type") !=null && request.getParameter("type").equals("single")) {
            int user_id = request.getParameter("updateId") == "" ? 0 : Integer.parseInt(request.getParameter("updateId"));
            User user = user_dao.getUser(user_id);
            Gson gson = new GsonBuilder().create();
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(gson.toJson(user));
        } else {
            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setAdmin(Boolean.parseBoolean(request.getParameter("isAdmin")));
            user.setPassword(request.getParameter("password"));

            UserDao userDao = new UserDao();
            UserAndResult user_with_ids = userDao.saveUser(user);
            Gson gson = new Gson();
            String message = "";
            User user_got = user_with_ids.getUser();
            if(user_got.getUser_id() > 0) {
                message = gson.toJson(user_got);
            } else {
                message = user_with_ids.getResult() == SaveUserResult.error ? "{ \"message\": \"El usuario no se pudo agregar por un error interno\" , \"type\":\"error\" }":"{ \"message\": \"El nombre de usuario ya está tomado, elija otro\",\"type\":\"repeated\" }" ;
            }

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(message);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if(request.getSession().getAttribute("isAdmin")==null || !(boolean)request.getSession().getAttribute("isAdmin"))
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        try {
            //String username = request.getParameter("username");
            String isAdmin = request.getParameter("isAdmin");
            boolean changePassword = Boolean.parseBoolean(request.getParameter("changePassword"));
            String password = request.getParameter("password");
            String userId = request.getParameter("user_id");

            User user = new User();
            user.setUser_id(Integer.parseInt(userId));
            //user.setUsername(username);
            user.setAdmin(Boolean.parseBoolean(isAdmin));
            user.setPassword(password);
            UserDao userDao = new UserDao();
            boolean wasUpdated = userDao.updateUser(user,changePassword);
            Gson gson = new Gson();
            String message = "";
            if (wasUpdated) {
                message = "{ \"message\": \"El usuario se modificó correctamente\" }";
            } else {
                message = "{ \"message\": \"El usuario no se modificó\" }";
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
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        if(request.getSession().getAttribute("isAdmin")==null || !(boolean)request.getSession().getAttribute("isAdmin"))
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        int id = request.getParameter("deleteId") == "" ? 0 : Integer.parseInt(request.getParameter("deleteId"));
        UserDao userDao = new UserDao();
        boolean wasDeleted = userDao.deleteUser(id);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String message = wasDeleted ? "{\"message\": \"Borrado exitoso\"}" : "{\"message\": \"Borrado falló\"}";
        out.print(message);
    }
}
