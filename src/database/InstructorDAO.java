package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Provides database operations related to instructors.
 */
public class InstructorDAO {

    public static void insertInstructor(int id, String name, String email, String department) {
        String createTableSql = """
                CREATE TABLE IF NOT EXISTS Instructors (
                    instructor_id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    email TEXT NOT NULL,
                    department TEXT NOT NULL
                )
                """;

        String insertSql = "INSERT INTO Instructors(instructor_id, name, email, department) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.connect();
             Statement createStatement = connection.createStatement()) {

            createStatement.execute(createTableSql);

            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                statement.setInt(1, id);
                statement.setString(2, name);
                statement.setString(3, email);
                statement.setString(4, department);

                statement.executeUpdate();
                System.out.println("Instructor saved to database successfully.");
            }

        } catch (Exception e) {
            System.out.println("Failed to save instructor: " + e.getMessage());
        }
    }
    public static boolean deleteInstructor(String email) {
        String deleteSql = "DELETE FROM Instructors WHERE email = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(deleteSql)) {

            statement.setString(1, email);
            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {
            System.out.println("Failed to delete instructor: " + e.getMessage());
            return false;
        }
    }
}