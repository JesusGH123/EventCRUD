package com.example.ProyectoFinal.dao;

import com.example.ProyectoFinal.model.User;

import java.util.List;

public interface IUserDao {
    //List<User> getUsers();
    User getUser(String user, String password);
    //User saveUser(User user);
    //boolean deleteUser(int user_id);
    //boolean updateUser(User user);
}
