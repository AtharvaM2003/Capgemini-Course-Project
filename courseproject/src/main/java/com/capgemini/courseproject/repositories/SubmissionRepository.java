package com.capgemini.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
import com.capgemini.courseproject.entities.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long>{
		
	List<Submission> findByUserUserId(Long userId);
    List<Submission> findByAssignmentId(Long assignmentId);
}
