package com.capgemini.courseproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.courseproject.entities.Submission;

import com.capgemini.courseproject.services.SubmissionService;


@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
	
	SubmissionService submissionService;
	
	@Autowired
	public SubmissionController(SubmissionService submissionService) {
		this.submissionService = submissionService;
	}
	
	@GetMapping
	public ResponseEntity<List<Submission>> getAllSubmissions() {
		List<Submission> submissionList = submissionService.findAllsubmissions();
		return ResponseEntity.status(HttpStatus.OK).body(submissionList);

	}
	
	@GetMapping("/{submissionId}")
	public ResponseEntity<Submission> getSubmission(@PathVariable Long submissionId) {
		Submission submission = submissionService.findSubmissionById(submissionId);
		return ResponseEntity.status(HttpStatus.OK).body(submission);

	}
	
//	@DeleteMapping("/{submissionId}")
//	public ResponseEntity<Submission> deleteSubmission(@PathVariable Long submissionId) {
//		submissionService.deleteSubmission(submissionId);
//		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//	}
	
	

}
