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


    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }


    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department addedDepartment = departmentService.addDepartment(department);
        return new ResponseEntity<>(addedDepartment, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        Department updatedDepartment = departmentService.updateDepartment(id, department);
        return ResponseEntity.ok(updatedDepartment);
    }


    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateDepartment(@PathVariable Long id) {
        departmentService.deactivateDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
