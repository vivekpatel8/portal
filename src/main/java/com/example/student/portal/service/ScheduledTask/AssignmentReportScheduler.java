package com.example.student.portal.service.ScheduledTask;

import com.example.student.portal.model.StudentAssignmentReport;
import com.example.student.portal.model.StudentAssignmentSubmission;
import com.example.student.portal.service.StudentAssignmentSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssignmentReportScheduler {

    @Autowired
    private StudentAssignmentSubmissionService submissionService;

    @Scheduled(cron = "0 0 * * * *") // Run once every hour
    public void generateAssignmentReport() {
        // Get the list of unprocessed assignment submissions
        List<StudentAssignmentSubmission> unprocessedSubmissions = submissionService.getUnprocessedSubmissions();

        // Process each unprocessed submission and generate the report
        for (StudentAssignmentSubmission submission : unprocessedSubmissions) {
            double fileSizeInKb = submission.getFileSize() / 1024.0;
            long submissionDateInMillis = submission.getSubmissionDate().getTime();
            long assignmentStartDateInMillis = submission.getAssignment().getAssignmentStartDate().getTime();

            // Calculate the score based on the provided formula
            double score = fileSizeInKb + (submissionDateInMillis - assignmentStartDateInMillis);
            score = Math.round(score * 10000.0) / 10000.0; // Round to 4 digits

            // Create and save the student assignment report
            StudentAssignmentReport report = new StudentAssignmentReport();
            report.setStudent(submission.getStudent());
            report.setAssignment(submission.getAssignment());
            report.setScore(score);

            // Save the report and mark the submission as processed
            submissionService.processSubmission(submission, report);
        }
    }
}
