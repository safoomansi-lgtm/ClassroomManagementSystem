

package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Provides database operations related to students.
 */
public class StudentDAO {

    public static void insertStudent(int id, String name, String email, String studentNumber) {
        String createTableSql = """
                CREATE TABLE IF NOT EXISTS Students (
                    student_id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    email TEXT NOT NULL,
                    student_number TEXT NOT NULL
                )
                """;

        String insertSql = "INSERT INTO Students(student_id, name, email, student_number) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.connect();
             Statement createStatement = connection.createStatement()) {

            createStatement.execute(createTableSql);

            try (PreparedStatement statement = connection.prepareStatement(insertSql)) {
                statement.setInt(1, id);
                statement.setString(2, name);
                statement.setString(3, email);
                statement.setString(4, studentNumber);

                statement.executeUpdate();
                System.out.println("Student saved to database successfully.");
            }

        } catch (Exception e) {
            System.out.println("Failed to save student: " + e.getMessage());
        }
    }
}