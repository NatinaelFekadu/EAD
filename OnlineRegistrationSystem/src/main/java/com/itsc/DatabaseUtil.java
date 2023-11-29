package com.itsc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/project1?useSSL=false";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "N@ti(7652)";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
}

