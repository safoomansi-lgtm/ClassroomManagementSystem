package model;
import exception.InvalidDataException;

/**
 * Represents a student in the classroom management system.
 * This Class extend and adds a student number.
 */
public class Student extends Person {

    private String studentNumber;

    public Student(int id, String name, String email, String studentNumber) throws InvalidDataException {
        super(id, name, email);
        if (name == null || name.isEmpty()){
            throw new InvalidDataException("model.Student name cannot be empty");
        }
        this.studentNumber = studentNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public void displayRole() {
        System.out.println("Role: model.Student");
    }
    @Override
    public String toString() {
        return "model.Student Number: " + studentNumber +
                ", Name: " + getName();
    }
}