package com.capgemini.courseproject.services;

import com.capgemini.courseproject.dto.AssignmentDto;
import com.capgemini.courseproject.entities.Assignment;
import java.util.List;
import java.util.Optional;

public interface AssignmentService {
	AssignmentDto addAssignment(AssignmentDto assignmentDto);

	Optional<Assignment> getAssignmentById(Long assignmentId);

	List<AssignmentDto> getAllAssignments();
	
	Assignment updateAssignment(Long assignmentId, Assignment updatedAssignment);

}
