package com.example.ProyectoFinal.controller;

import com.example.ProyectoFinal.dao.UserDao;
import com.example.ProyectoFinal.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("username");
        String password = request.getParameter("password");

        //Form validations
        if(!user.equals("") && !password.equals("")) {
            UserDao userDao = new UserDao();
            User userVal = userDao.getUser(user, password);

            //DB validation
            if(userVal != null) {
                response.sendRedirect("home.jsp");
            } else {
                request.setAttribute("message", "Error al iniciar sesión. Usuario o contraseña inexistentes");
                request.getRequestDispatcher("login.jsp?auth_type=1").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Usuario o contraseña incorrecta");
            request.getRequestDispatcher("login.jsp?auth_type=1").forward(request, response);
        }
    }
}