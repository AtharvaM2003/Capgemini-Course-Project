package com.capgemini.courseproject;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import com.capgemini.courseproject.controllers.InstructorController;
import com.capgemini.courseproject.entities.Course;
import com.capgemini.courseproject.entities.Instructor;
import com.capgemini.courseproject.services.InstructorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

class InstructorControllerTest {

	@Mock
	private InstructorService instructorService;

	@InjectMocks
	private InstructorController instructorController;

	@Mock
	private BindingResult bindingResult;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindInstructorById() {

		Long instructorId = 1L;
		Instructor mockInstructor = new Instructor();
		mockInstructor.setInstructorId(instructorId);
		mockInstructor.setName("Ram");

		when(instructorService.findInstructorById(instructorId)).thenReturn(mockInstructor);

		ResponseEntity<Instructor> response = instructorController.findInstructorById(instructorId);

		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getInstructorId()).isEqualTo(instructorId);
		assertThat(response.getBody().getName()).isEqualTo("Ram");
	}

	@Test
	void testCreateInstructor() {
		Course course = new Course();
		course.setCourseId(2L);
		course.setTitle("Java Basics");

		List<Course> courses = new ArrayList<>();
		courses.add(course);

		Instructor instructor = new Instructor(1L, "Ram", "Java", courses);

		when(bindingResult.hasErrors()).thenReturn(false);
		when(instructorService.createInstructor(instructor)).thenReturn(instructor);

		ResponseEntity<Instructor> response = instructorController.createInstructor(instructor, bindingResult);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody()).isEqualTo(instructor);
	}

	@Test
	void testUpdateInstructor() {
		Long instructorId = 1L;

		Instructor updatedInstructor = new Instructor();
		updatedInstructor.setInstructorId(instructorId);
		updatedInstructor.setName("Ram");
		updatedInstructor.setExpertise("Spring Boot");

		when(bindingResult.hasErrors()).thenReturn(false);
		when(instructorService.updateInstructor(any(Instructor.class), eq(instructorId))).thenReturn(updatedInstructor);

		ResponseEntity<Instructor> response = instructorController.updateInstructor(instructorId, updatedInstructor,
				bindingResult);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getInstructorId()).isEqualTo(instructorId);
		assertThat(response.getBody().getName()).isEqualTo("Ram");
		assertThat(response.getBody().getExpertise()).isEqualTo("Spring Boot");
	}

	@Test
	void testDeleteInstructorById() {

		Long instructorId = 1L;

		ResponseEntity<Instructor> response = instructorController.deleteInstructor(instructorId);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
