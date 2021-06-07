package com.example.ProyectoFinal.dao;

import com.example.ProyectoFinal.model.User;
import com.example.ProyectoFinal.utility.MySQLConnection;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao implements IUserDao{
    final String getUser = "SELECT * FROM User WHERE user_id = ? AND password = ?";
    @Override
    public User getUser(String user, String password) {

        User userResult = null;

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(getUser);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, password);

            ResultSet resultset = preparedStatement.executeQuery();

            if(resultset.next()) {
                userResult = new User();

                userResult.setUser_id(resultset.getInt("user_id"));
                userResult.setUsername(resultset.getString("username"));
                userResult.setAdmin(resultset.getBoolean("is_admin"));
                userResult.setPassword(resultset.getString("password"));

                return userResult;
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
}
