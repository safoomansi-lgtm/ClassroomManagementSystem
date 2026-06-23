package gui;

import database.CourseDAO;
import database.EnrollmentDAO;
import database.InstructorDAO;
import database.StudentDAO;
import database.SummaryDAO;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main graphical user interface of the classroom management system.
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Classroom Management System");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Classroom Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        JButton addStudentButton = createButton("Add Student");
        JButton addCourseButton = createButton("Add Course");
        JButton addInstructorButton = createButton("Add Instructor");
        JButton enrollStudentButton = createButton("Enroll Student in Course");
        JButton viewSummaryButton = createButton("View Summary");

        addStudentButton.addActionListener(e -> showAddStudentForm());
        addCourseButton.addActionListener(e -> showAddCourseForm());
        addInstructorButton.addActionListener(e -> showAddInstructorForm());
        enrollStudentButton.addActionListener(e -> showEnrollStudentForm());
        viewSummaryButton.addActionListener(e -> showSummary());

        JPanel mainPanel = new JPanel(new GridLayout(6, 1, 15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        mainPanel.add(createPanel(titleLabel));
        mainPanel.add(createPanel(addStudentButton));
        mainPanel.add(createPanel(addCourseButton));
        mainPanel.add(createPanel(addInstructorButton));
        mainPanel.add(createPanel(enrollStudentButton));
        mainPanel.add(createPanel(viewSummaryButton));

        add(mainPanel);
    }

    /**
     * Creates a styled button for the main interface.
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(280, 55));
        return button;
    }

    /**
     * Places a component inside a centered panel.
     */
    private JPanel createPanel(Component component) {
        JPanel panel = new JPanel();
        panel.add(component);
        return panel;
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
     * Displays a form for entering course information and saves it to the database.
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
            String creditHoursText = creditHoursField.getText();

            if (courseName.isEmpty() || courseCode.isEmpty() || creditHoursText.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all course fields.");
            } else {
                try {
                    int creditHours = Integer.parseInt(creditHoursText);

                    CourseDAO.insertCourse(
                            (int) (System.currentTimeMillis() % 100000),
                            courseName,
                            courseCode,
                            creditHours
                    );

                    JOptionPane.showMessageDialog(this,
                            "Course saved to database successfully!");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Credit hours must be a number.");
                }
            }
        }
    }

    /**
     * Displays a form for entering instructor information and saves it to the database.
     */
    private void showAddInstructorForm() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField departmentField = new JTextField();

        Object[] fields = {
                "Instructor Name:", nameField,
                "Email:", emailField,
                "Department:", departmentField
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Add Instructor",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String email = emailField.getText();
            String department = departmentField.getText();

            if (name.isEmpty() || email.isEmpty() || department.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all instructor fields.");
            } else {
                InstructorDAO.insertInstructor(
                        (int) (System.currentTimeMillis() % 100000),
                        name,
                        email,
                        department
                );

                JOptionPane.showMessageDialog(this,
                        "Instructor saved to database successfully!");
            }
        }
    }

    /**
     * Displays a form for enrolling a student in a course.
     */
    private void showEnrollStudentForm() {
        JTextField studentNumberField = new JTextField();
        JTextField courseCodeField = new JTextField();

        Object[] fields = {
                "Student Number:", studentNumberField,
                "Course Code:", courseCodeField
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Enroll Student in Course",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String studentNumber = studentNumberField.getText();
            String courseCode = courseCodeField.getText();

            if (studentNumber.isEmpty() || courseCode.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in student number and course code.");
            } else {
                EnrollmentDAO.enrollStudent(studentNumber, courseCode);

                JOptionPane.showMessageDialog(this,
                        "Student enrolled in course successfully!");
            }
        }
    }

    /**
     * Displays a summary of the stored classroom data.
     */
    private void showSummary() {
        int studentsCount = SummaryDAO.countStudents();
        int coursesCount = SummaryDAO.countCourses();
        int instructorsCount = SummaryDAO.countInstructors();
        int enrollmentsCount = SummaryDAO.countEnrollments();
        String studentsPerCourse = SummaryDAO.getStudentsPerCourseSummary();

        JOptionPane.showMessageDialog(this,
                "Classroom Management Summary\n\n"
                        + "Total Students: " + studentsCount + "\n"
                        + "Total Courses: " + coursesCount + "\n"
                        + "Total Instructors: " + instructorsCount + "\n"
                        + "Total Enrollments: " + enrollmentsCount + "\n\n"
                        + "Students per Course:\n"
                        + studentsPerCourse,
                "System Summary",
                JOptionPane.INFORMATION_MESSAGE);
    }
}