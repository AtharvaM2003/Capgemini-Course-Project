package com.capgemini.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.courseproject.dto.AssignmentDto;
import com.capgemini.courseproject.entities.Assignment;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	List<Assignment> findByCourse_CourseId(Long courseId);
	
	 @Query("SELECT new com.capgemini.courseproject.dto.AssignmentDto(" +
	           "a.assignmentId, a.title, a.description, c.courseId, c.title) " +
	           "FROM Assignment a JOIN a.course c")
	    List<AssignmentDto> findAllAssignmentDtos();
	 
	 

}