package com.example.student.portal.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "student_assignment_submissions")
public class StudentAssignmentSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submission_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "a_id", nullable = false)
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "s_id", nullable = false)
    private Student student;

    @Column(name = "submission_date")
    private Date submissionDate;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "submitted_by")
    private String submittedBy;

    @Column(name = "processed_flag")
    private boolean processedFlag;


    public void setProcessedFlag(boolean processedFlag) {
        this.processedFlag = processedFlag;
    }


    // Default constructor
    public StudentAssignmentSubmission() {
    }

    // Parameterized constructor
    public StudentAssignmentSubmission(Assignment assignment, Student student, Date submissionDate, byte[] file,
                                       Long fileSize, String fileName, String submittedBy) {
        this.assignment = assignment;
        this.student = student;
        this.submissionDate = submissionDate;
        this.file = file;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.submittedBy = submittedBy;
    }

    // Getters and Setters for other fields

    // Getter and Setter for id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean canSubmit() {
        return assignment != null &&
                assignment.isActive() &&
                assignment.getAssignmentEndDate().after(new Date());
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy) {
        this.submittedBy = submittedBy;
    }
}

