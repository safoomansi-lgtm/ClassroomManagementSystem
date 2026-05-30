public class Submission {

    private int submissionId;
    private Student student;
    private Assignment assignment;
    private String submissionDate;
    private String status;

    public Submission(int submissionId, Student student, Assignment assignment, String submissionDate, String status) {
        this.submissionId = submissionId;
        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = status;
    }

    public void displaySubmissionInfo() {
        System.out.println("Submission ID: " + submissionId);
        System.out.println("Student: " + student.getName());
        System.out.println("Assignment: " + assignment.getTitle());
        System.out.println("Submission Date: " + submissionDate);
        System.out.println("Status: " + status);
    }
}
