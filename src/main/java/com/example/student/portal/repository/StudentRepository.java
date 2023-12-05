package com.example.student.portal.repository;

import com.example.student.portal.model.Department;
import com.example.student.portal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByDepartment(Department department);
}