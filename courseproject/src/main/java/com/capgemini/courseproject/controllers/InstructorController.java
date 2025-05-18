package com.capgemini.courseproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.courseproject.dto.InstructorWiseStudentDto;
import com.capgemini.courseproject.entities.Instructor;
import com.capgemini.courseproject.services.InstructorService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/instructors")
@Slf4j
public class InstructorController {

	private final InstructorService instructorService;

	@Autowired
	public InstructorController(InstructorService instructorService) {
		super();
		this.instructorService = instructorService;
	}

	@GetMapping
	public ResponseEntity<List<Instructor>> findAllInnstrctors() {
		log.info("GET /api/instructors - Request received to fetch all instructors");
		List<Instructor> instructorList = instructorService.findAllInstructor();
		log.debug("Returning {} instructors", instructorList.size());
		return ResponseEntity.status(HttpStatus.OK).body(instructorList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Instructor> findInstructorById(@PathVariable Long id) {
		log.info("GET /api/instructors/{} - Request received to fetch instructor by ID", id);
		Instructor instructor = instructorService.findInstructorById(id);
		log.debug("Fetched Instructor: {}", instructor);
		return ResponseEntity.status(HttpStatus.OK).body(instructor);
	}

	@PostMapping
	public ResponseEntity<Instructor> createInstructor(@Valid @RequestBody Instructor instructor,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		log.info("POST /api/instructors - Request received to create instructor: {}", instructor);
		Instructor save = instructorService.createInstructor(instructor);
		log.debug("Instructor created with ID: {}", save.getInstructorId());
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @Valid @RequestBody Instructor instructor,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		log.info("PUT /api/instructors/{} - Request received to update instructor: {}", id, instructor);
		Instructor newInstructor = instructorService.updateInstructor(instructor, id);
		log.debug("Instructor with ID {} updated: {}", id, newInstructor);
		return ResponseEntity.status(HttpStatus.OK).body(newInstructor);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Instructor> deleteInstructor(@PathVariable Long id) {
		log.info("DELETE /api/instructors/{} - Request received to delete instructor", id);
		instructorService.deleteInstructor(id);
		log.info("Instructor with ID {} successfully deleted", id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping("/iwisestudent")
    public ResponseEntity<List<InstructorWiseStudentDto>> getInstructorWiseStudentCount() {
        List<InstructorWiseStudentDto> data = instructorService.getInstructorWiseStudentCount();
        return ResponseEntity.ok(data);
    }
	
	@PostMapping("/{instructorId}/assign/{courseId}")
	public ResponseEntity<Void> enrollStudent(@PathVariable Long instructorId, @PathVariable Long courseId) {
		instructorService.assignInstructorToCourse(instructorId, courseId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}