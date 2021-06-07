package com.example.ProyectoFinal.controller;

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

        //Mejorar validacion del correo y contraseña
        if(user.equals("123") && password.equals("123")) {
            // Verificar si el usuario existe
        } else {
            request.setAttribute("message", "Usuario o contraseña incorrecta");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
