package com.capgemini.courseproject;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.capgemini.courseproject.controllers.AssignmentController;
import com.capgemini.courseproject.dto.AssignmentDto;
import com.capgemini.courseproject.entities.Assignment;
import com.capgemini.courseproject.services.AssignmentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class AssignmentControllerTest {

	@Mock
	private AssignmentService assignmentService;

	@Mock
	private BindingResult bindingResult;

	@InjectMocks
	private AssignmentController assignmentController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllAssignments() {
		AssignmentDto a1 = new AssignmentDto();
		a1.setAssignmentId(1L);
		a1.setTitle("Assignment 1");
		a1.setDescription("Desc 1");

		AssignmentDto a2 = new AssignmentDto();
		a2.setAssignmentId(2L);
		a2.setTitle("Assignment 2");
		a2.setDescription("Desc 2");

		when(assignmentService.getAllAssignments()).thenReturn(Arrays.asList(a1, a2));

		ResponseEntity<List<AssignmentDto>> response = assignmentController.getAllAssignments();

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).hasSize(2);
		assertThat(response.getBody().get(0).getTitle()).isEqualTo("Assignment 1");
	}

	@Test
	void testGetAssignmentByIdFound() {
		Assignment assignment = new Assignment();
		assignment.setAssignmentId(1L);
		assignment.setTitle("Assignment X");

		when(assignmentService.getAssignmentById(1L)).thenReturn(Optional.of(assignment));

		ResponseEntity<Assignment> response = assignmentController.getAssignmentById(1L);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getAssignmentId()).isEqualTo(1L);
		assertThat(response.getBody().getTitle()).isEqualTo("Assignment X");
	}

	@Test
	void testGetAssignmentByIdNotFound() {
		when(assignmentService.getAssignmentById(99L)).thenReturn(Optional.empty());

		ResponseEntity<Assignment> response = assignmentController.getAssignmentById(99L);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isNull();
	}

	@Test
	void testCreateAssignmentValid() {
	    AssignmentDto input = new AssignmentDto();
	    input.setTitle("New Assignment");
	    input.setDescription("New Description");

	    AssignmentDto saved = new AssignmentDto();
	    saved.setAssignmentId(10L);
	    saved.setTitle("New Assignment");
	    saved.setDescription("New Description");

	    when(bindingResult.hasErrors()).thenReturn(false);
	    when(assignmentService.addAssignment(any(AssignmentDto.class))).thenReturn(saved);

	    ResponseEntity<AssignmentDto> response = assignmentController.addAssignment(input, bindingResult);

	    verify(bindingResult).hasErrors();
	    verify(assignmentService).addAssignment(any(AssignmentDto.class));

	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	    assertThat(response.getBody()).isNotNull();
	    assertThat(response.getBody().getAssignmentId()).isEqualTo(10L);
	}


	@Test
	void testCreateAssignmentInvalid() {
		AssignmentDto input = new AssignmentDto();
		input.setTitle("");

		when(bindingResult.hasErrors()).thenReturn(true);

		assertThatThrownBy(() -> {
			assignmentController.addAssignment(input, bindingResult);
		}).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Invalid assignment data");
	}

}
