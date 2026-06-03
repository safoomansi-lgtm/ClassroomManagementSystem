package model;

/**
 * Represents a student's submission for an assignment.
 * A submission links a student with an assignment.
 */
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
        System.out.println("model.Submission ID: " + submissionId);
        System.out.println("model.Student: " + student.getName());
        System.out.println("model.Assignment: " + assignment.getTitle());
        System.out.println("model.Submission Date: " + submissionDate);
        System.out.println("Status: " + status);
    }
}
