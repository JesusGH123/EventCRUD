package com.example.ProyectoFinal.controller;

import com.example.ProyectoFinal.dao.UserDao;
import com.example.ProyectoFinal.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserController", value = "/user")
@MultipartConfig
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //if(session.getAttribute("user") != null) {
            UserDao userDao = new UserDao();
            List<User> users = userDao.getUsers();
            request.setAttribute("users", users);
            request.getRequestDispatcher("users.jsp").forward(request, response);
        //} else
            request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String isAdmin = request.getParameter("isAdmin");
        String password = request.getParameter("password");

        System.out.println("Is admin? :" + Boolean.parseBoolean(isAdmin));

        User user = new User();
        user.setUsername(username);
        user.setAdmin(Boolean.parseBoolean(isAdmin));
        user.setPassword(password);

        UserDao userDao = new UserDao();
        User newUser = userDao.saveUser(user);

        request.getRequestDispatcher("users.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String username = request.getParameter("username");
        String isAdmin = request.getParameter("isAdmin");
        String password = request.getParameter("password");
        String userId = request.getParameter("user_id");

        User user = new User();
        user.setUser_id(Integer.parseInt(userId));
        user.setUsername(username);
        user.setAdmin(Boolean.parseBoolean(isAdmin));
        user.setPassword(password);

        UserDao userDao = new UserDao();
        User newUser = userDao.post(user);

        request.getRequestDispatcher("users.jsp").forward(request, response);*/

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = request.getParameter("deleteId") == "" ? 0 : Integer.parseInt(request.getParameter("deleteId"));

        UserDao userDao = new UserDao();
        userDao.deleteUser(id);
    }
}
