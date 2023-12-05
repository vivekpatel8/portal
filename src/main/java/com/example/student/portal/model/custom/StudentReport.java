package com.example.student.portal.model.custom;

import com.example.student.portal.model.Student;
import com.example.student.portal.model.StudentAssignmentSubmission;

import java.util.List;

public class StudentReport {

    private Student student;
    private List<StudentAssignmentSubmission> submissions;

    public StudentReport(Student student, List<StudentAssignmentSubmission> submissions) {
        this.student = student;
        this.submissions = submissions;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<StudentAssignmentSubmission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<StudentAssignmentSubmission> submissions) {
        this.submissions = submissions;
    }
}
