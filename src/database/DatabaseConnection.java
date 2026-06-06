package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Handles the database connection for the classroom management system.
 */
public class DatabaseConnection {

    private static final String URL = "jdbc:sqlite:classroom.db";

    public static Connection connect() {
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
            System.out.println("Database connected successfully.");
        } catch (Exception e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }

        return connection;
    }
}
