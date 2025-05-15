package com.capgemini.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.courseproject.entities.Assignment;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	List<Assignment> findByCourse_CourseId(Long courseId);
}