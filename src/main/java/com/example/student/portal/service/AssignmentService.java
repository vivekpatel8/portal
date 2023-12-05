package com.example.student.portal.service;

import com.example.student.portal.model.Assignment;
import com.example.student.portal.model.Student;
import com.example.student.portal.repository.AssignmentRepository;
import com.example.student.portal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private StudentRepository studentRepository;
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Assignment getAssignmentById(Long id) {
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(id);
        return optionalAssignment.orElse(null);
    }

    public Assignment addAssignment(Assignment assignment) {
        // Additional logic before saving, if needed
        return assignmentRepository.save(assignment);
    }

    public Assignment updateAssignment(Long id, Assignment updatedAssignment) {
        Optional<Assignment> optionalExistingAssignment = assignmentRepository.findById(id);

        if (optionalExistingAssignment.isPresent()) {
            Assignment existingAssignment = optionalExistingAssignment.get();

            // Update fields
            existingAssignment.setAssignmentName(updatedAssignment.getAssignmentName());
            existingAssignment.setAssignmentStartDate(updatedAssignment.getAssignmentStartDate());
            existingAssignment.setAssignmentEndDate(updatedAssignment.getAssignmentEndDate());
            // Update other fields as needed

            // Save and return the updated assignment
            return assignmentRepository.save(existingAssignment);
        } else {
            // Assignment not found
            return null;
        }
    }

    public void deactivateAssignment(Long id) {
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(id);

        if (optionalAssignment.isPresent()) {
            Assignment assignment = optionalAssignment.get();
            assignment.setActiveFlag(false); // Mark as inactive
            assignmentRepository.save(assignment);
        }
    }
    public void assignAssignmentToStudents(Long assignmentId, List<Long> studentIds) throws Exception{
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(assignmentId);

        if (optionalAssignment.isPresent()) {
            Assignment assignment = optionalAssignment.get();

            List<Student> students = studentRepository.findAllById(studentIds);

            // Add the assignment to each selected student
            students.forEach(student -> student.getAssignments().add(assignment));

            // Save students to update the assignment association
            studentRepository.saveAll(students);
        } else {
            System.out.println("Assignment is not present");

            throw new Exception("Assignment not present for: "+ assignmentId);
        }
    }
}
