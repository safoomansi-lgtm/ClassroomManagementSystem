package main;

import exception.InvalidDataException;
import model.*;

public class Main {

    public static void main(String[] args) {

        try {

            Instructor instructor =
                    new Instructor(1, "Ahmed Ali",
                            "ahmed@gmail.com",
                            "Computer Science");

            Course course =
                    new Course(101,
                            "Java Programming",
                            instructor);
            System.out.println(course);

            Student student =
                    new Student(1,
                            "Safa",
                            "safa@gmail.com",
                            "S100");
            System.out.println(student);

            Assignment assignment =
                    new Assignment(1,
                            "OOP Homework",
                            "25/05/2026",
                            course);
            Submission submission =
                    new Submission(
                            1,
                            student,
                            assignment,
                            "2026-05-31",
                            "Submitted"
                    );

            submission.displaySubmissionInfo();
            student.displayRole();
            instructor.displayRole();

            course.add();
            assignment.add();
System.out.println(assignment);
        }

        catch (InvalidDataException e) {

            System.out.println(e.getMessage());

        }

    }

}
