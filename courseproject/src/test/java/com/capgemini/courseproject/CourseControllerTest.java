package com.capgemini.courseproject;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.capgemini.courseproject.controllers.CourseController;
import com.capgemini.courseproject.entities.Course;
import com.capgemini.courseproject.services.CourseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CourseControllerTest {

	@Mock
	private CourseService courseService;

	@InjectMocks
	private CourseController courseController;

	@InjectMocks
	private BindingResult bindingResult;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateCourse() {
		Course inputCourse = new Course(null, "Spring Boot", "Backend course", null, null, null, 0.0);
		Course savedCourse = new Course(1L, "Spring Boot", "Backend course", null, null, null, 0.0);

		when(courseService.addCourse(any(Course.class))).thenReturn(savedCourse);

		ResponseEntity<Course> response = courseController.addCourse(inputCourse, bindingResult);

		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getCourseId()).isEqualTo(1L);
		assertThat(response.getBody().getTitle()).isEqualTo("Spring Boot");
	}

	@Test
	void testGetCourseById() {
		Course course = new Course(1L, "Java", "Intro to Java", null, null, null, 0.0);
		when(courseService.getCourseById(1L)).thenReturn(Optional.of(course));

		ResponseEntity<Course> response = courseController.getCourseById(1L);

		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getCourseId()).isEqualTo(1L);
		assertThat(response.getBody().getTitle()).isEqualTo("Java");
	}

	@Test
	void testGetAllCourses() {
		List<Course> courses = Arrays.asList(new Course(1L, "Java", "Basics", null, null, null, 0.0),
				new Course(2L, "Python", "Data Science", null, null, null, 0.0));

		when(courseService.getAllCourses()).thenReturn(courses);

		ResponseEntity<List<Course>> response = courseController.getAllCourses();

		assertThat(response.getBody()).hasSize(2);
	}

	@Test
	void testUpdateCourse() {
		Course updated = new Course(1L, "Advanced Java", "Updated course", null, null, null, 0.0);
		when(courseService.updateCourse(eq(1L), any(Course.class))).thenReturn(updated);

		ResponseEntity<Course> response = courseController.updateCourse(1L, updated, bindingResult);

		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getTitle()).isEqualTo("Advanced Java");
	}

	@Test
	void testDeleteCourse() {
		doNothing().when(courseService).deleteCourse(1L);

		ResponseEntity<Void> response = courseController.deleteCourse(1L);

		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
	}
}
