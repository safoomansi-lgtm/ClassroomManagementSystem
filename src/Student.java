
public class Student extends Person {

    private String studentNumber;

    public Student(int id, String name, String email, String studentNumber) throws InvalidDataException {
        super(id, name, email);
        if (name == null || name.isEmpty()){
            throw new InvalidDataException("Student name cannot be empty");
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
        System.out.println("Role: Student");
    }
    @Override
    public String toString() {
        return "Student Number: " + studentNumber +
                ", Name: " + getName();
    }
}