package com.example.student.portal.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "student_assignment_reports")
public class StudentAssignmentReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "s_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "a_id", nullable = false)
    private Assignment assignment;

    @Column(name = "score")
    private Double score;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;


    public StudentAssignmentReport() {
    }


    public StudentAssignmentReport(Student student, Assignment assignment, Double score) {
        this.student = student;
        this.assignment = assignment;
        this.score = score;
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
