
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
    public static boolean deleteCourse(String courseCode) {
        String deleteEnrollmentsSql = "DELETE FROM Enrollments WHERE course_code = ?";
        String deleteCourseSql = "DELETE FROM Courses WHERE course_code = ?";

        try (Connection connection = DatabaseConnection.connect()) {

            try (PreparedStatement enrollmentStatement = connection.prepareStatement(deleteEnrollmentsSql)) {
                enrollmentStatement.setString(1, courseCode);
                enrollmentStatement.executeUpdate();
            }

            try (PreparedStatement courseStatement = connection.prepareStatement(deleteCourseSql)) {
                courseStatement.setString(1, courseCode);
                int rowsDeleted = courseStatement.executeUpdate();

                return rowsDeleted > 0;
            }

        } catch (Exception e) {
            System.out.println("Failed to delete course: " + e.getMessage());
            return false;
        }
    }
}