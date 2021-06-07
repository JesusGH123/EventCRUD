package com.example.ProyectoFinal.utility;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    public static Connection getConnection() {
        String connectionString = "jdbc:mysql://events-morelos.cimdx99sppfo.us-east-1.rds.amazonaws.com:3306/events?user=admin&password=dpU2[Z:v6SkChK";

        try {
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(connectionString);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        Connection connection = getConnection();

        if(connection != null){
            System.out.println("Database successfully connected");
        }
        else {
            System.out.println("Error trying to connect to database");
        }

    }
}
