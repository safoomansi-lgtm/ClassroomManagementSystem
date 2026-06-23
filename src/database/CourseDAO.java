
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Provides database operations related to courses.
 */
public class CourseDAO {

    public static void insertCourse(int id, String courseName, String courseCode, int creditHours) {
        String createTableSql = """
                CREATE TABLE IF NOT EXISTS Courses (
                    course_id INTEGER PRIMARY KEY,
                    course_name TEXT NOT NULL,
                    course_code TEXT NOT NULL,
                    credit_hours INTEGER NOT NULL
                )
                """;

        String insertSql = "INSERT INTO Courses(course_id, course_name, course_code, credit_hours) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.connect();
             Statement createStatement = connection.createStatement()) {

            createStatement.execute(createTableSql);

            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                statement.setInt(1, id);
                statement.setString(2, courseName);
                statement.setString(3, courseCode);
                statement.setInt(4, creditHours);

                statement.executeUpdate();
                System.out.println("Course saved to database successfully.");
            }

        } catch (Exception e) {
            System.out.println("Failed to save course: " + e.getMessage());
        }
    }
}