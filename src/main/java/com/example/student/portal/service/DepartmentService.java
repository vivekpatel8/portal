package com.example.student.portal.service;

import com.example.student.portal.model.Department;
import com.example.student.portal.model.Student;
import com.example.student.portal.repository.DepartmentRepository;
import com.example.student.portal.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    public Department addDepartment(Department department) {
        // Additional logic before saving, if needed
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        Optional<Department> optionalExistingDepartment = departmentRepository.findById(id);

        if (optionalExistingDepartment.isPresent()) {
            Department existingDepartment = optionalExistingDepartment.get();

            // Update fields
            existingDepartment.setDepartmentName(updatedDepartment.getDepartmentName());
            existingDepartment.setHodName(updatedDepartment.getHodName());
            // Update other fields as needed

            // Save and return the updated department
            return departmentRepository.save(existingDepartment);
        } else {
            // Department not found
            return null;
        }
    }

    public void deactivateDepartment(Long id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);

        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            department.setActiveFlag(false); // Mark as inactive
            departmentRepository.save(department);
        }
    }
}
