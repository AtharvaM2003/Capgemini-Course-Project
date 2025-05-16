package com.capgemini.courseproject;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.capgemini.courseproject.controllers.SubmissionController;
import com.capgemini.courseproject.entities.Assignment;
import com.capgemini.courseproject.entities.Submission;
import com.capgemini.courseproject.entities.User;
import com.capgemini.courseproject.services.SubmissionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

class SubmissionControllerTest {

	@Mock
	private SubmissionService submissionService;

	@InjectMocks
	private SubmissionController submissionController;
	
	@Mock
	private BindingResult bindingResult;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllSubmissions() {
		User mockUser = new User(1L, "Alice", "alice@gmail.com", "pass123", "9876543210", "student");
		Assignment mockAssignment = new Assignment();

		Submission submission1 = new Submission(1L, mockAssignment, mockUser, LocalDate.now(), true);
		Submission submission2 = new Submission(2L, mockAssignment, mockUser, LocalDate.now().minusDays(1), false);

		when(submissionService.findAllsubmissions()).thenReturn(Arrays.asList(submission1, submission2));

		ResponseEntity<List<Submission>> response = submissionController.getAllSubmissions();

		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody()).hasSize(2);
		assertThat(response.getBody().get(0).getSubmissionId()).isEqualTo(1L);
		assertThat(response.getBody().get(1).getStatus()).isFalse();
	}

	@Test
	void testGetSubmissionById() {
		User mockUser = new User(1L, "Alice", "alice@gmail.com", "pass123", "9876543210", "student");
		Assignment mockAssignment = new Assignment();
		Submission mockSubmission = new Submission(1L, mockAssignment, mockUser, LocalDate.now(), true);

		when(submissionService.findSubmissionById(1L)).thenReturn(mockSubmission);

		ResponseEntity<Submission> response = submissionController.getSubmission(1L);

		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getSubmissionId()).isEqualTo(1L);
		assertThat(response.getBody().getStatus()).isTrue();
	}
	
	@Test
	void testCreateSubmission() {
	    
	    User mockUser = new User(1L, "Alice", "alice@gmail.com", "pass123", "9876543210", "student", null, null);
	    Assignment mockAssignment = new Assignment();
	    Submission submissionToCreate = new Submission(null, mockAssignment, mockUser, LocalDate.now(), true);
	    Submission savedSubmission = new Submission(1L, mockAssignment, mockUser, LocalDate.now(), true);

	    
	    when(submissionService.createSubmission(submissionToCreate)).thenReturn(savedSubmission);

	   
	    ResponseEntity<Submission> response = submissionController.createSubmission(submissionToCreate, bindingResult );

	    
	    assertThat(response.getBody()).isNotNull();
	    assertThat(response.getBody().getSubmissionId()).isEqualTo(1L);
	    assertThat(response.getBody().getStatus()).isTrue();
	}

	
	
	
	
	
	


}
