
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

            Student student =
                    new Student(1,
                            "Safa",
                            "safa@gmail.com",
                            "S100");

            Assignment assignment =
                    new Assignment(1,
                            "OOP Homework",
                            "25/05/2026",
                            course);

            student.displayRole();
            instructor.displayRole();

            course.add();
            assignment.add();

        }

        catch (InvalidDataException e) {

            System.out.println(e.getMessage());

        }

    }

}
