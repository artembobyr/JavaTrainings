package app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BookingConnector {
    private static final String username = "user@localhost";
    private static final String password = "somepass";
    private static final String url = "jdbc:mysql://localhost:3306/bookingsdb";
    private static Connection connection=null;

    public static Connection getConnection() {
        return connection;
    }

    public Statement getStatment() {
        try {
            if (connection==null) {
                connection = DriverManager.getConnection(url, username, password);
            }
            Statement statement =connection.createStatement();
            return statement;

        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
            return null;
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Connection is not created ");
        }
    }
}
