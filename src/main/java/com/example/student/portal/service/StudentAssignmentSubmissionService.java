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
        // Fetch assignment
        Assignment assignment = assignmentRepository.findById(assignmentId).orElse(null);

        // Fetch student
        Student student = studentRepository.findById(studentId).orElse(null);

        // Check if the assignment can be submitted
        if (assignment != null && student != null && assignment.isActive() && assignment.getAssignmentEndDate().after(new Date())) {
            StudentAssignmentSubmission submission = new StudentAssignmentSubmission();
            submission.setAssignment(assignment);
            submission.setStudent(student);
            submission.setSubmissionDate(new Date());
            submission.setFile(file);
            submission.setFileSize((long) file.length);
            // You may want to handle file name in a more sophisticated way
            submission.setFileName("assignment_submission.docx");
            submission.setSubmittedBy(submittedBy);

            // Save the submission
            submissionRepository.save(submission);
        } else {
            // Assignment cannot be submitted
            // Handle the error as needed
            throw new Exception("Assignment Can not be submitted");
        }
    }
    public List<StudentAssignmentSubmission> getUnprocessedSubmissions() {
        // Implement logic to retrieve unprocessed submissions
        // For example, you might fetch submissions with a status flag indicating whether they are processed or not
        // Adjust this method based on your specific database schema and criteria
        return submissionRepository.findByProcessedFlag(false);
    }

    public void processSubmission(StudentAssignmentSubmission submission, StudentAssignmentReport report) {
        // Save the report
        reportRepository.save(report);

        // Mark the submission as processed
        submission.setProcessedFlag(true);
        submissionRepository.save(submission);
    }
}
