package com.example.student.portal.controller;

import com.example.student.portal.model.Student;
import com.example.student.portal.model.custom.StudentReport;
import com.example.student.portal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Get all students
    @GetMapping("/get")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get a student by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    // Add a new student
    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student addedStudent = studentService.addStudent(student);
        return new ResponseEntity<>(addedStudent, HttpStatus.CREATED);
    }

    // Update a student
    @PutMapping("/add/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    // Deactivate (mark active_flag as N) a student
    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<Void> deactivateStudent(@PathVariable Long id) {
        studentService.deactivateStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byDepartment/{departmentName}")
    public ResponseEntity<List<Student>> getStudentsByDepartmentName(@PathVariable String departmentName) {
        List<Student> students = studentService.getStudentsByDepartmentName(departmentName);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/withoutSubmissions")
    public ResponseEntity<List<Student>> getStudentsWithoutSubmissions() {
        List<Student> students = studentService.getStudentsWithoutSubmissions();
        return ResponseEntity.ok(students);
    }
    // Get a report for a specific student
    @GetMapping("/{studentId}/report")
    public ResponseEntity<StudentReport> getStudentReport(@PathVariable Long studentId) {
        StudentReport studentReport = studentService.getStudentReport(studentId);

        if (studentReport != null) {
            return ResponseEntity.ok(studentReport);
        } else {
            // Handle the case when the student is not found
            return ResponseEntity.notFound().build();
        }
    }
}


/*
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello Students";
    }

    @GetMapping()
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.of(Optional.ofNullable(student));
    }

    @PostMapping("/add")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
*/
