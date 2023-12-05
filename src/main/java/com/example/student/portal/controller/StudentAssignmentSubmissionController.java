package com.example.student.portal.controller;

import com.example.student.portal.service.StudentAssignmentSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/assignmentSubmissions")
public class StudentAssignmentSubmissionController {

    @Autowired
    private StudentAssignmentSubmissionService submissionService;


    @PostMapping("/{assignmentId}/submit/{studentId}")
    public ResponseEntity<String> submitAssignment(
            @PathVariable() Long assignmentId,
            @PathVariable() Long studentId,
            @RequestParam(name = "file") MultipartFile file,
            @RequestParam(name = "submittedBy") String submittedBy) {
        try {
            byte[] fileBytes = file.getBytes();
            submissionService.submitAssignment(assignmentId, studentId, fileBytes, submittedBy);
            return ResponseEntity.status(HttpStatus.CREATED).body("Assignment submitted successfully.");
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting assignment.");
        }
    }
}
