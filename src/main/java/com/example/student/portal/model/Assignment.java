package com.example.student.portal.model;


import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assignments")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private Long id;

    @Column(name = "assignment_name")
    private String assignmentName;

    @Column(name = "assignment_start_date")
    private Date assignmentStartDate;

    @Column(name = "assignment_end_date")
    private Date assignmentEndDate;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "active_flag")
    private boolean activeFlag;

    @ManyToMany(mappedBy = "assignments")
    private Set<Student> students = new HashSet<>();

    // ... (existing methods)

    // Getter and Setter for students
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    // Constructors, getters, setters, and other methods

    // Default constructor
    public Assignment() {
    }

    // Parameterized constructor
    public Assignment(String assignmentName, Date assignmentStartDate, Date assignmentEndDate) {
        this.assignmentName = assignmentName;
        this.assignmentStartDate = assignmentStartDate;
        this.assignmentEndDate = assignmentEndDate;
        this.createdDate = new Date();
        this.updatedDate = new Date();
        this.activeFlag = true;
    }

    // Getters and Setters for other fields

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public Date getAssignmentStartDate() {
        return assignmentStartDate;
    }

    public void setAssignmentStartDate(Date assignmentStartDate) {
        this.assignmentStartDate = assignmentStartDate;
    }

    public Date getAssignmentEndDate() {
        return assignmentEndDate;
    }

    public void setAssignmentEndDate(Date assignmentEndDate) {
        this.assignmentEndDate = assignmentEndDate;
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

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public boolean isActive() {
        Date currentDate = new Date();
        return activeFlag && assignmentStartDate.before(currentDate) && assignmentEndDate.after(currentDate);
    }
}
