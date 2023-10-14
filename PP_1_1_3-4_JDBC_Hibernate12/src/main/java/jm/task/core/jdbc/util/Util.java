package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final static String USERNAME ="root";
    private final static String PASSWORD = "2003";
    public static void main(String[] args)  {

    }
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            if (!connection.isClosed()) {
                System.out.println("Cоединение с БД установлено");
            }

            if (connection.isClosed()) {
                System.out.println("Cоединение с БД закрыто");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }

        return connection;
    }
}
