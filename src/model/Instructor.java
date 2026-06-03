package model;

/**
 * Represents an instructor in the classroom management system.
 * This Class extends person and contains instructor information.
 */

public class Instructor extends Person {

    private String department;

    public Instructor(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public void displayRole() {
        System.out.println("Role: model.Instructor");
    }
}
