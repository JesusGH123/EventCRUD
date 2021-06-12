package com.example.ProyectoFinal.dao;

import com.example.ProyectoFinal.model.User;
import com.example.ProyectoFinal.utility.MySQLConnection;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao{
    final String GET_USER_And_ADMIN_INFO = "call get_user_id_and_admin_info ( ? , ? )";
    final String GET_USER_By_ID = "SELECT * FROM user WHERE user_id = ?";
    final String CREATE_USER = "call create_user(?, ?, ?)";
    final String GET_ALL_USERS = "call get_all_users()";
    final String DELETE_USER = "call delete_user(?)";
    final String UPDATE_USER = "call edit_user(?, ?, ?, ?)";

    @Override
    public List<User> getUsers() {
        User user = null;
        List<User> users = new ArrayList<User>();

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                user = new User();
                user.setUser_id(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setAdmin(resultSet.getBoolean("is_admin"));
                user.setPassword(resultSet.getString("password"));  //Not necessary

                users.add(user);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return users;
    }

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

    @Override
    public User getUser(int user_id) {
        User userResult = null;

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_By_ID);
            preparedStatement.setInt(1, user_id);

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

    @Override
    public User saveUser(User user) {

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setBoolean(2, user.isAdmin());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.executeUpdate();

            user.setUsername(user.getUsername());
            user.setAdmin(user.isAdmin());
            user.setPassword(user.getPassword());

            return user;

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public boolean deleteUser(int user_id) {

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, user_id);

            int deleted = preparedStatement.executeUpdate();

            if(deleted == 1) {
                System.out.println("Registro eliminado");
                return true;
            } else {
                System.out.println("Error en el borrado");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }

    @Override
    public boolean updateUser(User user) {

        try {
            Connection connection = MySQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
            preparedStatement.setInt(1, user.getUser_id());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setBoolean(3, user.isAdmin());
            preparedStatement.setString(4, user.getPassword());

            int updated = preparedStatement.executeUpdate();
            if(updated == 1){
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }
}
