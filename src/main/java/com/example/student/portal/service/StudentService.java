package com.example.student.portal.service;

import com.example.student.portal.model.Department;
import com.example.student.portal.model.Student;
import com.example.student.portal.model.StudentAssignmentSubmission;
import com.example.student.portal.model.custom.StudentReport;
import com.example.student.portal.repository.DepartmentRepository;
import com.example.student.portal.repository.StudentAssignmentSubmissionRepository;
import com.example.student.portal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    StudentAssignmentSubmissionRepository submissionRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsWithoutSubmissions() {
        List<Student> allStudents = studentRepository.findAll();
        List<Long> studentsWithSubmissions = submissionRepository.findAll()
                .stream()
                .map(submission -> submission.getStudent().getId())
                .collect(Collectors.toList());

        return allStudents.stream()
                .filter(student -> !studentsWithSubmissions.contains(student.getId()))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsByDepartmentName(String departmentName) {
        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentName(departmentName);

        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            return studentRepository.findByDepartment(department);
        } else {
            return List.of();
        }
    }
    public Student getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        return optionalStudent.orElse(null);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Optional<Student> optionalExistingStudent = studentRepository.findById(id);

        if (optionalExistingStudent.isPresent()) {
            Student existingStudent = optionalExistingStudent.get();


            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setCity(updatedStudent.getCity());
            return studentRepository.save(existingStudent);
        } else {
            return null;
        }
    }

    public void deactivateStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setActiveFlag(false); // Mark as inactive
            studentRepository.save(student);
        }
    }


    public StudentReport getStudentReport(Long studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null) {
            // Fetch the student's assignment submissions
            List<StudentAssignmentSubmission> submissions = submissionRepository.findByStudentId(studentId);

            // Create and return a StudentReport object
            return new StudentReport(student, submissions);
        } else {
            return null;
        }
    }
}
