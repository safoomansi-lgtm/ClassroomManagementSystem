package model;
import service.Manageable;

/**
 * Represents an assignment in a course.
 * Each assignment has a title and due date.
 */
public class Assignment implements Manageable {

    private int assignmentId;
    private String title;
    private String dueDate;
    private Course course;

    public Assignment(int assignmentId, String title, String dueDate, Course course) {
        this.assignmentId = assignmentId;
        this.title = title;
        this.dueDate = dueDate;
        this.course = course;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public void add() {
        System.out.println("model.Assignment added: " + title);
    }

    @Override
    public void update() {
        System.out.println("model.Assignment updated: " + title);
    }

    @Override
    public void delete() {
        System.out.println("model.Assignment deleted: " + title);
    }
    @Override
    public String toString(){
        return  "model.Assignment ID: " +assignmentId + "Tittle:" +title +"Due Date: "+ dueDate;
    }
}