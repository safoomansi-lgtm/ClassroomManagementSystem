package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Classroom Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Classroom Management System", SwingConstants.CENTER);
        JButton addStudentButton = new JButton("Add Student");
        JButton addCourseButton = new JButton("Add Course");

        setLayout(new GridLayout(3, 1));
        add(titleLabel);
        add(addStudentButton);
        add(addCourseButton);
    }
}