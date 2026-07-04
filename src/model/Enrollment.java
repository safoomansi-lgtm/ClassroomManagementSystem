package model;

/**
 * Represents the enrollment of a student in a course.
 */
public class Enrollment {

    private int enrollmentId;
    private int studentId;
    private int courseId;

    /**
     * Creates an Enrollment object.
     *
     * @param enrollmentId the enrollment ID
     * @param studentId the student ID
     * @param courseId the course ID
     */
    public Enrollment(int enrollmentId, int studentId, int courseId) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}