package com.example.student.portal.repository;
import com.example.student.portal.model.StudentAssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAssignmentSubmissionRepository extends JpaRepository<StudentAssignmentSubmission, Long> {
    List<StudentAssignmentSubmission> findByStudentId(Long studentId);

    List<StudentAssignmentSubmission> findByProcessedFlag(boolean b);
}
