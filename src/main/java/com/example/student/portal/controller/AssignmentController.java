package com.example.student.portal.controller;

import com.example.student.portal.model.Assignment;
import com.example.student.portal.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    // Get all assignments
    @GetMapping
    public List<Assignment> getAllAssignments() {
        return assignmentService.getAllAssignments();
    }

    // Get an assignment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        return ResponseEntity.ok(assignment);
    }

    // Add a new assignment
    @PostMapping
    public ResponseEntity<Assignment> addAssignment(@RequestBody Assignment assignment) {
        Assignment addedAssignment = assignmentService.addAssignment(assignment);
        return new ResponseEntity<>(addedAssignment, HttpStatus.CREATED);
    }

    // Update an assignment
    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @RequestBody Assignment assignment) {
        Assignment updatedAssignment = assignmentService.updateAssignment(id, assignment);
        return ResponseEntity.ok(updatedAssignment);
    }

    // Deactivate (marking active_flag as N) an assignment
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateAssignment(@PathVariable Long id) {
        assignmentService.deactivateAssignment(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{assignmentId}/assignStudents")
    public ResponseEntity<Void> assignAssignmentToStudents(
            @PathVariable Long assignmentId,
            @RequestBody List<Long> studentIds) throws Exception {
        assignmentService.assignAssignmentToStudents(assignmentId, studentIds);
        return ResponseEntity.noContent().build();
    }
}
