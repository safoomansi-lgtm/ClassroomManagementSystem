package Demo;

import exception.InvalidDataException;
import model.Person;
import model.Student;
import model.Instructor;

public class PolymorphismDemo {

    public static void runDemo() {
        try {
            Person person;

            person = new Student(1, "Ali", "ali@email.com", "S001");
            person.displayRole();

            person = new Instructor(2, "Sara", "sara@email.com", "Computer Science");
            person.displayRole();

        } catch (InvalidDataException e) {
            System.out.println("Invalid data: " + e.getMessage());
        }
    }
}