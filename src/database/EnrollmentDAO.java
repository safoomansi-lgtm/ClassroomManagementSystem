package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Provides database operations related to student course enrollments.
 */
public class EnrollmentDAO {

    public static void enrollStudent(String studentNumber, String courseCode) {
        String createTableSql = """
                CREATE TABLE IF NOT EXISTS Enrollments (
                    enrollment_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    student_number TEXT NOT NULL,
                    course_code TEXT NOT NULL
                )
                """;

        String insertSql = "INSERT INTO Enrollments(student_number, course_code) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.connect();
             Statement createStatement = connection.createStatement()) {

            createStatement.execute(createTableSql);

            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                statement.setString(1, studentNumber);
                statement.setString(2, courseCode);

                statement.executeUpdate();
                System.out.println("Student enrolled in course successfully.");
            }

        } catch (Exception e) {
            System.out.println("Failed to enroll student: " + e.getMessage());
        }
    }
    public static boolean deleteEnrollment(String studentNumber, String courseCode) {
        String deleteSql = "DELETE FROM Enrollments WHERE student_number = ? AND course_code = ?";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement statement = connection.prepareStatement(deleteSql)) {

            statement.setString(1, studentNumber);
            statement.setString(2, courseCode);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {
            System.out.println("Failed to delete enrollment: " + e.getMessage());
            return false;
        }
    }
}