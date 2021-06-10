package com.example.ProyectoFinal.dao;

import com.example.ProyectoFinal.model.User;
import com.example.ProyectoFinal.utility.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao implements IUserDao{
    final String GET_USER_And_ADMIN_INFO = "call get_user_id_and_admin_info ( ? , ? )";
    @Override
    public User getUser(String user, String password) {

        User userResult=null;

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_And_ADMIN_INFO);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

            ResultSet resultset = preparedStatement.executeQuery();

            if(resultset.next()) {
                userResult = new User();
                userResult.setUser_id(resultset.getInt("user_id"));
                userResult.setUsername(user);
                userResult.setAdmin(resultset.getBoolean("is_admin"));
                userResult.setPassword(password);
                return userResult;
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
}
