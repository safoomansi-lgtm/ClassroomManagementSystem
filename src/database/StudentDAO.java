
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Provides database operations related to students.
 */
public class StudentDAO {

    public static void insertStudent(int id, String name, String email, String studentNumber) {
        String sql = "INSERT INTO Students(student_id, name, email, student_number) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, email);
            statement.setString(4, studentNumber);

            statement.executeUpdate();
            System.out.println("Student saved to database successfully.");

        } catch (Exception e) {
            System.out.println("Failed to save student: " + e.getMessage());
        }
    }
}
