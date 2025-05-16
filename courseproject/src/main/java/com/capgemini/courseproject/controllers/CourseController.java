package com.capgemini.courseproject.controllers;

import com.capgemini.courseproject.dto.CourseEnrollmentDto;
import com.capgemini.courseproject.entities.Assignment;
import com.capgemini.courseproject.entities.Course;
import com.capgemini.courseproject.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/courses")
@Slf4j
public class CourseController {

	private CourseService courseService;

	@Autowired
	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@PostMapping
	public ResponseEntity<Course> addCourse(@Valid @RequestBody Course course, BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		log.info("POST /api/courses - Adding new course: {}", course.getTitle());
		Course savedCourse = courseService.addCourse(course);
		log.debug("Saved course details: {}", savedCourse);
		return ResponseEntity.ok(savedCourse);
	}

	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses() {
		log.info("GET /api/courses - Request received to fetch all courses");
		List<Course> courses = courseService.getAllCourses();
		log.debug("Returning {} courses", courses.size());
		return ResponseEntity.ok(courses);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
		log.info("GET /api/courses/{} - Fetching course by ID", id);
		Optional<Course> course = courseService.getCourseById(id);
		if (course.isPresent()) {
			log.debug("Course found: {}", course.get());
		} else {
			log.warn("Course with ID {} not found", id);
		}
		return course.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable Long id, @Valid @RequestBody Course updatedCourse,
			BindingResult result) {
		if (result.hasErrors()) {

			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		log.info("PUT /api/courses/{} - Updating course", id);
		Course course = courseService.updateCourse(id, updatedCourse);
		if (course != null) {
			log.debug("Updated course: {}", course);
			return ResponseEntity.ok(course);
		} else {
			log.warn("Failed to update course with ID {} - not found", id);
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
		log.info("DELETE /api/courses/{} - Deleting course", id);
		courseService.deleteCourse(id);
		log.debug("Deleted course with ID {}", id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}/assignments")
	public ResponseEntity<List<Assignment>> getAssignmentsByCourseId(@PathVariable Long id) {
		log.info("GET /api/courses/{}/assignments - Fetching assignments for course", id);
		List<Assignment> assignments = courseService.findByCourseCourseId(id);
		if (assignments.isEmpty()) {
			log.warn("No assignments found for course ID {}", id);
			return ResponseEntity.notFound().build();
		}
		log.debug("Found {} assignments for course ID {}", assignments.size(), id);
		return ResponseEntity.ok(assignments);
	}

	@GetMapping("/coursereport")
	public ResponseEntity<List<CourseEnrollmentDto>> getCourseEnrollmentReport() {
		List<CourseEnrollmentDto> report = courseService.getCourseEnrollmentReport();
		return ResponseEntity.status(HttpStatus.OK).body(report);
	}


	@GetMapping("/instructor/{instructorId}")
	public List<String> getCourseTitlesByInstructor(@PathVariable Long instructorId) {
		return courseService.getCourseTitlesByInstructorId(instructorId);
	}

}
