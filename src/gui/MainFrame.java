package gui;

import database.StudentDAO;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main graphical user interface of the classroom management system.
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Classroom Management System");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Classroom Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));

        JButton addStudentButton = new JButton("Add Student");
        JButton addCourseButton = new JButton("Add Course");

        addStudentButton.setFont(new Font("Arial", Font.BOLD, 16));
        addCourseButton.setFont(new Font("Arial", Font.BOLD, 16));

        addStudentButton.setPreferredSize(new Dimension(220, 55));
        addCourseButton.setPreferredSize(new Dimension(220, 55));

        addStudentButton.addActionListener(e -> showAddStudentForm());
        addCourseButton.addActionListener(e -> showAddCourseForm());

        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JPanel studentPanel = new JPanel();
        studentPanel.add(addStudentButton);

        JPanel coursePanel = new JPanel();
        coursePanel.add(addCourseButton);

        mainPanel.add(titleLabel);
        mainPanel.add(studentPanel);
        mainPanel.add(coursePanel);

        add(mainPanel);
    }

    /**
     * Displays a form for entering student information and saves it to the database.
     */
    private void showAddStudentForm() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField studentNumberField = new JTextField();

        Object[] fields = {
                "Student Name:", nameField,
                "Email:", emailField,
                "Student Number:", studentNumberField
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Add Student",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String email = emailField.getText();
            String studentNumber = studentNumberField.getText();

            if (name.isEmpty() || email.isEmpty() || studentNumber.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all student fields.");
            } else {
                StudentDAO.insertStudent(
                        (int) (System.currentTimeMillis() % 100000),
                        name,
                        email,
                        studentNumber
                );

                JOptionPane.showMessageDialog(this,
                        "Student saved to database successfully!");
            }
        }
    }

    /**
     * Displays a form for entering course information.
     */
    private void showAddCourseForm() {
        JTextField courseNameField = new JTextField();
        JTextField courseCodeField = new JTextField();
        JTextField creditHoursField = new JTextField();

        Object[] fields = {
                "Course Name:", courseNameField,
                "Course Code:", courseCodeField,
                "Credit Hours:", creditHoursField
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Add Course",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String courseName = courseNameField.getText();
            String courseCode = courseCodeField.getText();
            String creditHours = creditHoursField.getText();

            if (courseName.isEmpty() || courseCode.isEmpty() || creditHours.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all course fields.");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Course added successfully!\n"
                                + "Course Name: " + courseName + "\n"
                                + "Course Code: " + courseCode + "\n"
                                + "Credit Hours: " + creditHours);
            }
        }
    }
}