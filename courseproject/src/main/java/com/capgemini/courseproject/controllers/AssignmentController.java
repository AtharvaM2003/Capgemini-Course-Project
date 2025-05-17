package com.capgemini.courseproject.controllers;

import com.capgemini.courseproject.dto.AssignmentDto;
import com.capgemini.courseproject.entities.Assignment;
import com.capgemini.courseproject.services.AssignmentService;
import com.sun.net.httpserver.Authenticator.Result;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

}
