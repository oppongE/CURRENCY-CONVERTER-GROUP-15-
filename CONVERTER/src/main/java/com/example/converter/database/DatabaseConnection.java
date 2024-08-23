package com.example.converter.database;

import java.sql.*;
public class DatabaseConnection {
    public static Connection connect() {
        final String URL = "jdbc:mysql://localhost:3306/converter";
        final String Username = "root";
        final String Password = "BackCase1@";

        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL, Username, Password);

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
