package com.capgemini.courseproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.courseproject.dto.AssignmentDto;
import com.capgemini.courseproject.entities.Assignment;
import com.capgemini.courseproject.services.AssignmentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/assignments")
@Slf4j
public class AssignmentController {

	private final AssignmentService assignmentService;


	@Autowired
	public AssignmentController(AssignmentService assignmentService) {
		
		this.assignmentService = assignmentService;
	
	}

	@GetMapping
	public ResponseEntity<List<AssignmentDto>> getAllAssignments() {
		log.info("GET /api/assignments - Request received to fetch all assignments");
		List<AssignmentDto> assignmentList = assignmentService.getAllAssignments();
		log.debug("Returning {} assignments", assignmentList.size());
		return ResponseEntity.status(HttpStatus.OK).body(assignmentList);
	}

	

	@GetMapping("/{id}")
	public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
		log.info("GET /api/assignments/{} -Request fetching assignment by id", id);
		Optional<Assignment> assignment = assignmentService.getAssignmentById(id);
		if (assignment.isPresent()) {
			log.debug("Assignment found!!", assignment.get());
		} else {
			log.warn("Assignment not found!!", id);
		}
		return assignment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<AssignmentDto> addAssignment(@RequestBody AssignmentDto assignmentDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException(bindingResult.getFieldErrors().toString());
		}
		AssignmentDto createdAssignment = assignmentService.addAssignment(assignmentDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAssignment);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @Valid @RequestBody Assignment updatedAssignment,
			BindingResult result) {
		if (result.hasErrors()) {

			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		log.info("PUT /api/assignments/{} - Updating assignment", id);
		Assignment assignment = assignmentService.updateAssignment(id, updatedAssignment);
		if (assignment != null) {
			log.debug("Updated assignment: {}", assignment);
			return ResponseEntity.ok(assignment);
		} else {
			log.warn("Failed to update assignment with ID {} - not found", id);
			return ResponseEntity.notFound().build();
		}
	}
	

	@PostMapping("/assignments/{assignmentId}/assign/{courseId}")
	public ResponseEntity<String> assignAssignmentToCourse(
	        @PathVariable Long assignmentId,
	        @PathVariable Long courseId) {

	    assignmentService.assignAssignmentToCourse(assignmentId, courseId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	
}
