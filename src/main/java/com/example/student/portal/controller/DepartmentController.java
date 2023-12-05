package com.example.student.portal.controller;

import com.example.student.portal.model.Department;
import com.example.student.portal.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Get all departments
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // Get a department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    // Add a new department
    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department addedDepartment = departmentService.addDepartment(department);
        return new ResponseEntity<>(addedDepartment, HttpStatus.CREATED);
    }

    // Update a department
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartment(id, department);
        return ResponseEntity.ok(updatedDepartment);
    }

    // Deactivate (mark active_flag as N) a department
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateDepartment(@PathVariable Long id) {
        departmentService.deactivateDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
