package com.example.ProyectoFinal.controller;

import com.example.ProyectoFinal.dao.UserDao;
import com.example.ProyectoFinal.model.User;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@MultipartConfig
@WebServlet(name = "login", value = "/login")
public class LoginController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String type = request.getParameter("type");
        if(type!=null && type.equals("logout")) {
            try {
                session.invalidate();
                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                String message = "{\"message\": \"Cerrar sesión exitoso\"}";
                out.print(message);
                //request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                System.out.println("logout finished");
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }else {
            //Form validations
            String user = request.getParameter("username");
            String password = request.getParameter("password");
            if (!user.equals("") && !password.equals("")) {
                UserDao userDao = new UserDao();
                User userVal = userDao.getUser(user, password);

                //DB validation
                if (userVal != null) {
                    session.setAttribute("username", user);
                    session.setAttribute("isAdmin", userVal.isAdmin());
                    //change to controller redirect
                    response.sendRedirect("event");
                } else {
                    request.setAttribute("message", "Error al iniciar sesión. Usuario o contraseña inexistentes");
                    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", "Usuario o contraseña incorrecta");
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        }
    }
}