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

        addStudentButton.addActionListener(e -> {
            StudentDAO.insertStudent(
                    (int) (System.currentTimeMillis() % 100000),
                    "Safa",
                    "safa@email.com",
                    "S100"
            );

            JOptionPane.showMessageDialog(this,
                    "Student saved to database successfully!");
        });

        addCourseButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Add Course button clicked successfully!"));

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
}