package com.example.student.portal.service;

import com.example.student.portal.model.Assignment;
import com.example.student.portal.model.Student;
import com.example.student.portal.model.StudentAssignmentReport;
import com.example.student.portal.model.StudentAssignmentSubmission;
import com.example.student.portal.repository.AssignmentRepository;
import com.example.student.portal.repository.StudentAssignmentReportRepository;
import com.example.student.portal.repository.StudentAssignmentSubmissionRepository;
import com.example.student.portal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class StudentAssignmentSubmissionService {

    @Autowired
    private StudentAssignmentSubmissionRepository submissionRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentAssignmentReportRepository reportRepository;

    public void submitAssignment(Long assignmentId, Long studentId, byte[] file, String submittedBy) throws Exception {

        Assignment assignment = assignmentRepository.findById(assignmentId).orElse(null);


        Student student = studentRepository.findById(studentId).orElse(null);
        if (assignment != null && student != null && assignment.isActive() && assignment.getAssignmentEndDate().after(new Date())) {
            StudentAssignmentSubmission submission = new StudentAssignmentSubmission();
            submission.setAssignment(assignment);
            submission.setStudent(student);
            submission.setSubmissionDate(new Date());
            submission.setFile(file);
            submission.setFileSize((long) file.length);
            submission.setFileName("assignment_submission.docx");
            submission.setSubmittedBy(submittedBy);
            submissionRepository.save(submission);
        } else {

            throw new Exception("Assignment Can not be submitted");
        }
    }
    public List<StudentAssignmentSubmission> getUnprocessedSubmissions() {
        return submissionRepository.findByProcessedFlag(false);
    }

    public void processSubmission(StudentAssignmentSubmission submission, StudentAssignmentReport report) {
        reportRepository.save(report);
        submission.setProcessedFlag(true);
        submissionRepository.save(submission);
    }
}
