package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Provides summary information from the database.
 */
public class SummaryDAO {

    public static int countStudents() {
        return countRecords("Students");
    }

    public static int countCourses() {
        return countRecords("Courses");
    }

    public static int countInstructors() {
        return countRecords("Instructors");
    }

    public static int countEnrollments() {
        return countRecords("Enrollments");
    }

    private static int countRecords(String tableName) {
        String sql = "SELECT COUNT(*) AS total FROM " + tableName;

        try (Connection connection = DatabaseConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
                return resultSet.getInt("total");
            }

        } catch (Exception e) {
            System.out.println("Failed to count records from " + tableName + ": " + e.getMessage());
        }

        return 0;
    }

    public static String getStudentsPerCourseSummary() {
        String sql = """
                SELECT Courses.course_name, Courses.course_code, COUNT(Enrollments.student_number) AS total_students
                FROM Courses
                LEFT JOIN Enrollments
                ON Courses.course_code = Enrollments.course_code
                GROUP BY Courses.course_code, Courses.course_name
                """;

        StringBuilder summary = new StringBuilder();

        try (Connection connection = DatabaseConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String courseCode = resultSet.getString("course_code");
                int totalStudents = resultSet.getInt("total_students");

                summary.append(courseName)
                        .append(" (")
                        .append(courseCode)
                        .append("): ")
                        .append(totalStudents)
                        .append(" student(s)\n");
            }

        } catch (Exception e) {
            return "Students per course is not available yet.";
        }

        if (summary.length() == 0) {
            return "No courses found yet.";
        }

        return summary.toString();
    }
}