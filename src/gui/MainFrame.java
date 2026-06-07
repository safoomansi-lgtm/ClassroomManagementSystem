package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Represents the main graphical user interface of the classroom management system.
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Classroom Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titleLabel = new JLabel("Classroom Management System", SwingConstants.CENTER);
        JButton addStudentButton = new JButton("Add Student");
        JButton addCourseButton = new JButton("Add Course");
        addStudentButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        "Add Student button clicked successfully!"));

        addCourseButton.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        " Add Course button clicked successfully!"));

        setLayout(new GridLayout(3, 1));
        add(titleLabel);
        add(addStudentButton);
        add(addCourseButton);
    }
}