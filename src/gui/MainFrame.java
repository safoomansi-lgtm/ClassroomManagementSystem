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

    private JLabel titleLabel;
    private JLabel languageLabel;

    private JButton addStudentButton;
    private JButton addCourseButton;
    private JButton addInstructorButton;
    private JButton enrollStudentButton;
    private JButton viewSummaryButton;
    private JButton deleteDataButton;

    private JComboBox<String> languageComboBox;

    public MainFrame() {
        setTitle("Classroom Management System");
        setSize(850, 760);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Classroom Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        languageLabel = new JLabel("Language:");
        languageLabel.setFont(new Font("Arial", Font.BOLD, 14));

        languageComboBox = new JComboBox<>(new String[]{"English", "العربية"});
        languageComboBox.setFont(new Font("Arial", Font.BOLD, 14));
        languageComboBox.addActionListener(e -> updateLanguage());

        addStudentButton = createButton("Add Student");
        addCourseButton = createButton("Add Course");
        addInstructorButton = createButton("Add Instructor");
        enrollStudentButton = createButton("Enroll Student in Course");
        viewSummaryButton = createButton("View Summary");
        deleteDataButton = createButton("Delete Data");

        addStudentButton.addActionListener(e -> showAddStudentForm());
        addCourseButton.addActionListener(e -> showAddCourseForm());
        addInstructorButton.addActionListener(e -> showAddInstructorForm());
        enrollStudentButton.addActionListener(e -> showEnrollStudentForm());
        viewSummaryButton.addActionListener(e -> showSummary());
        deleteDataButton.addActionListener(e -> showDeleteDataForm());

        JPanel languagePanel = new JPanel();
        languagePanel.add(languageLabel);
        languagePanel.add(languageComboBox);

        JPanel mainPanel = new JPanel(new GridLayout(8, 1, 12, 12));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        mainPanel.add(languagePanel);
        mainPanel.add(createPanel(titleLabel));
        mainPanel.add(createPanel(addStudentButton));
        mainPanel.add(createPanel(addCourseButton));
        mainPanel.add(createPanel(addInstructorButton));
        mainPanel.add(createPanel(enrollStudentButton));
        mainPanel.add(createPanel(viewSummaryButton));
        mainPanel.add(createPanel(deleteDataButton));

        add(mainPanel);
    }

    /**
     * Updates the main interface language between English and Arabic.
     */
    private void updateLanguage() {
        String selectedLanguage = (String) languageComboBox.getSelectedItem();

        if ("العربية".equals(selectedLanguage)) {
            setTitle("نظام إدارة الصفوف");
            titleLabel.setText("نظام إدارة الصفوف");
            languageLabel.setText("اللغة:");

            addStudentButton.setText("إضافة طالب");
            addCourseButton.setText("إضافة كورس");
            addInstructorButton.setText("إضافة معلم");
            enrollStudentButton.setText("تسجيل طالب في كورس");
            viewSummaryButton.setText("عرض الملخص");
            deleteDataButton.setText("حذف بيانات");

            applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        } else {
            setTitle("Classroom Management System");
            titleLabel.setText("Classroom Management System");
            languageLabel.setText("Language:");

            addStudentButton.setText("Add Student");
            addCourseButton.setText("Add Course");
            addInstructorButton.setText("Add Instructor");
            enrollStudentButton.setText("Enroll Student in Course");
            viewSummaryButton.setText("View Summary");
            deleteDataButton.setText("Delete Data");

            applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        }

        revalidate();
        repaint();
    }

    /**
     * Creates a styled button for the main interface.
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(320, 55));
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
     * Displays a form for deleting data from the system.
     */
    private void showDeleteDataForm() {
        String[] options = {
                "Delete Student",
                "Delete Course",
                "Delete Instructor",
                "Delete Enrollment"
        };

        String selectedOption = (String) JOptionPane.showInputDialog(
                this,
                "Choose what you want to delete:",
                "Delete Data",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (selectedOption == null) {
            return;
        }

        switch (selectedOption) {
            case "Delete Student" -> deleteStudent();
            case "Delete Course" -> deleteCourse();
            case "Delete Instructor" -> deleteInstructor();
            case "Delete Enrollment" -> deleteEnrollment();
            default -> JOptionPane.showMessageDialog(this, "Invalid delete option.");
        }
    }

    /**
     * Deletes a student using student number.
     */
    private void deleteStudent() {
        String studentNumber = JOptionPane.showInputDialog(this, "Enter Student Number:");

        if (studentNumber == null || studentNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Student number is required.");
            return;
        }

        boolean deleted = StudentDAO.deleteStudent(studentNumber);

        if (deleted) {
            JOptionPane.showMessageDialog(this, "Student deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Student not found.");
        }
    }

    /**
     * Deletes a course using course code.
     */
    private void deleteCourse() {
        String courseCode = JOptionPane.showInputDialog(this, "Enter Course Code:");

        if (courseCode == null || courseCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Course code is required.");
            return;
        }

        boolean deleted = CourseDAO.deleteCourse(courseCode);

        if (deleted) {
            JOptionPane.showMessageDialog(this, "Course deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Course not found.");
        }
    }

    /**
     * Deletes an instructor using email.
     */
    private void deleteInstructor() {
        String email = JOptionPane.showInputDialog(this, "Enter Instructor Email:");

        if (email == null || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Instructor email is required.");
            return;
        }

        boolean deleted = InstructorDAO.deleteInstructor(email);

        if (deleted) {
            JOptionPane.showMessageDialog(this, "Instructor deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Instructor not found.");
        }
    }

    /**
     * Deletes a student enrollment using student number and course code.
     */
    private void deleteEnrollment() {
        JTextField studentNumberField = new JTextField();
        JTextField courseCodeField = new JTextField();

        Object[] fields = {
                "Student Number:", studentNumberField,
                "Course Code:", courseCodeField
        };

        int result = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Delete Enrollment",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            String studentNumber = studentNumberField.getText();
            String courseCode = courseCodeField.getText();

            if (studentNumber.isEmpty() || courseCode.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in student number and course code.");
            } else {
                boolean deleted = EnrollmentDAO.deleteEnrollment(studentNumber, courseCode);

                if (deleted) {
                    JOptionPane.showMessageDialog(this,
                            "Enrollment deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Enrollment not found.");
                }
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