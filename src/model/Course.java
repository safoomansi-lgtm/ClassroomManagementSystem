package model;

import service.Manageable;

/**
 * Represents a course in the classroom management system.
 * A course is associated with an instructor.
 */
public class Course implements Manageable {

    private int courseId;
    private String courseName;
    private Instructor instructor;

    public Course(int courseId, String courseName, Instructor instructor) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.instructor = instructor;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public void add() {
        System.out.println("model.Course added: " + courseName);
    }

    @Override
    public void update() {
        System.out.println("model.Course updated: " + courseName);
    }

    @Override
    public void delete() {
        System.out.println("model.Course deleted: " + courseName);
    }

    @Override
    public String toString() {
        return "model.Course ID: " + courseId +
                ", model.Course Name: " + courseName;
    }
}
