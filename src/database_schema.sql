CREATE TABLE Students (
                          student_id INT PRIMARY KEY,
                          name VARCHAR(100),
                          email VARCHAR(100),
                          student_number VARCHAR(50)
);

CREATE TABLE Instructors (
                             instructor_id INT PRIMARY KEY,
                             name VARCHAR(100),
                             email VARCHAR(100),
                             specialization VARCHAR(100)
);

CREATE TABLE Courses (
                         course_id INT PRIMARY KEY,
                         course_name VARCHAR(100),
                         instructor_id INT,
                         FOREIGN KEY (instructor_id) REFERENCES Instructors(instructor_id)
);

CREATE TABLE Assignments (
                             assignment_id INT PRIMARY KEY,
                             title VARCHAR(100),
                             due_date DATE,
                             course_id INT,
                             FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

CREATE TABLE Submissions (
                             submission_id INT PRIMARY KEY,
                             student_id INT,
                             assignment_id INT,
                             submission_date DATE,
                             status VARCHAR(50),
                             FOREIGN KEY (student_id) REFERENCES Students(student_id),
                             FOREIGN KEY (assignment_id) REFERENCES Assignments(assignment_id)
);