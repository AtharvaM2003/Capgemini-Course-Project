package com.capgemini.courseproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.courseproject.dto.EnrollmentReportDTO;
import com.capgemini.courseproject.entities.Enrollment;
import com.capgemini.courseproject.services.EnrollmentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/enrollments")

public class EnrollmentController {

	private final EnrollmentService enrollmentService;

	@Autowired
	public EnrollmentController(EnrollmentService enrollmentService) {

		this.enrollmentService = enrollmentService;
	}

	@PostMapping
	public ResponseEntity<Enrollment> createEnrollment(@Valid @RequestBody Enrollment enrollment,
			BindingResult result) {

		if (result.hasErrors()) {

			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}

		log.info("Request to Create a new enrollment");

		Enrollment newEnroll = enrollmentService.addEnrollment(enrollment);

		log.debug("New Enrollment created with ID:{}", newEnroll);
		return ResponseEntity.ok(newEnroll);

	}

	@GetMapping
	public ResponseEntity<List<Enrollment>> getAllEnrollments() {

		log.info("Request recieved to fetch all Enrollments");
		List<Enrollment> allEnrollments = enrollmentService.getAllEnrollments();

		log.debug("Showing enrollments", allEnrollments.size());
		return ResponseEntity.ok(allEnrollments);

	}

	@GetMapping("/{enrollmentId}")
	public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long enrollmentId) {

		log.info("Request to get Enrollments by their ID");

		Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(enrollmentId);

		log.debug("Showing enrollments by their ID:{}", enrollment);
		return enrollment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}
	
	@GetMapping("/enrollment-report")
	public ResponseEntity<List<EnrollmentReportDTO>> getEnrollmentReport() {
	    List<EnrollmentReportDTO> report = enrollmentService.getEnrollmentReport();
	    return ResponseEntity.ok(report);  
	}


}
