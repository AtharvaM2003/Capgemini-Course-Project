package com.capgemini.courseproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.courseproject.entities.Submission;

import com.capgemini.courseproject.services.SubmissionService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/submissions")
@Slf4j
public class SubmissionController {
	
	SubmissionService submissionService;
	
	@Autowired
	public SubmissionController(SubmissionService submissionService) {
		this.submissionService = submissionService;
	}
	
	@GetMapping
	public ResponseEntity<List<Submission>> getAllSubmissions() {
		log.info("Request received to fetch all submissions");
		List<Submission> submissionList = submissionService.findAllsubmissions();
		log.debug("Fetched {} submissions", submissionList.size());
		return ResponseEntity.status(HttpStatus.OK).body(submissionList);

	}
	
	@GetMapping("/{submissionId}")
	public ResponseEntity<Submission> getSubmission(@PathVariable Long submissionId) {
		log.info("Request received to fetch submission by ID", submissionId);
		Submission submission = submissionService.findSubmissionById(submissionId);
		log.debug("Fetched Submission: {}", submission);

		return ResponseEntity.status(HttpStatus.OK).body(submission);

	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Submission>> getSubmissionsByUser(@PathVariable Long userId) {
		log.info("Request received to fetch submission by user ID", userId);
	    List<Submission> submissions = submissionService.findSubmissionsByUserId(userId);
	    log.debug("Fetched Submission by userId: {}", submissions.size(), userId);
	    return ResponseEntity.ok(submissions);
	}
	
	@GetMapping("/assignment/{assignmentId}")
	public ResponseEntity<List<Submission>> getSubmissionsByAssignment(@PathVariable Long assignmentId) {
		log.info("Request received to fetch submission by assignment ID", assignmentId);
	    List<Submission> submissions = submissionService.findSubmissionsByAssignmentId(assignmentId);
	    log.debug("Fetched Submission by assignmentId: {}", submissions.size(), assignmentId);
	    return ResponseEntity.ok(submissions);
	}

//	@DeleteMapping("/{submissionId}")
//	public ResponseEntity<Submission> deleteSubmission(@PathVariable Long submissionId) {
//		submissionService.deleteSubmission(submissionId);
//		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//	}
	
	

}
