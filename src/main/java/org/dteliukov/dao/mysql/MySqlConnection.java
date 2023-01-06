package org.dteliukov.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private static final String dbHost = "localhost";
    private static final String URL = "jdbc:mysql://localhost:3306/space_trip?useSSL=FALSE&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "JEsgWz28Cg56_*";


    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
