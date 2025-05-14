package com.capgemini.courseproject.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.courseproject.entities.Enrollment;
import com.capgemini.courseproject.services.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

	@Autowired
	EnrollmentService enrollmentService;

	@PostMapping
	public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {

		Enrollment newEnroll = enrollmentService.addEnrollment(enrollment);
		return ResponseEntity.ok(newEnroll);

	}
	
	@GetMapping
	public ResponseEntity<List<Enrollment>> getAllEnrollments(){
		
		List<Enrollment> allEnrollments = enrollmentService.getAllEnrollments();
		return ResponseEntity.ok(allEnrollments);
		
		
	}
	
	@GetMapping("/{enrollmentId}")
	public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long enrollmentId){
		
		Optional<Enrollment> enrollment = enrollmentService.getEnrollmentById(enrollmentId);
		
		return enrollment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
	}
	
	

}
