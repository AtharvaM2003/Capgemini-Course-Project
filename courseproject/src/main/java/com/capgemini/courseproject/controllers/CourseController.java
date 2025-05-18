package com.capgemini.courseproject.controllers;

import com.capgemini.courseproject.dto.AvailableCourseDto;
import com.capgemini.courseproject.dto.CourseDto;
import com.capgemini.courseproject.dto.CourseEnrollmentDto;
import com.capgemini.courseproject.dto.EnrolledCourseDto;
import com.capgemini.courseproject.dto.Top5CoursesDto;
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
	public ResponseEntity<List<CourseDto>> getAllCourses() {
		log.info("GET /api/courses - Request received to fetch all courses");
		List<CourseDto> courses = courseService.getAllCourses();
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
		log.info("GET /api/courses/coursereport - Fetching course enrollment report");
		List<CourseEnrollmentDto> report = courseService.getCourseEnrollmentReport();
		log.debug("Returning {} records in course enrollment report", report.size());
		return ResponseEntity.status(HttpStatus.OK).body(report);
	}

	@GetMapping("/instructor/{instructorId}")
	public List<String> getCourseTitlesByInstructor(@PathVariable Long instructorId) {
		log.info("GET /api/courses/instructor/{} - Fetching course titles by instructor ID", instructorId);
		List<String> titles = courseService.getCourseTitlesByInstructorId(instructorId);
		log.debug("Found {} course titles for instructor ID {}", titles.size(), instructorId);
		return titles;
	}

	@GetMapping("/available/{userid}")
	public ResponseEntity<List<AvailableCourseDto>> findCoursesWithIsEnrollment(@PathVariable Long userid) {
		log.info("GET /api/courses/available/{} - Fetching available courses for user", userid);
		List<AvailableCourseDto> availableCourses = courseService.findCoursesWithIsEnrollment(userid);
		log.debug("Returning {} available courses for user ID {}", availableCourses.size(), userid);
		return ResponseEntity.status(HttpStatus.OK).body(availableCourses);
	}

	@GetMapping("/enrolled/{userid}")
	public ResponseEntity<List<EnrolledCourseDto>> enrolledCoursesByStudent(@PathVariable Long userid) {
		log.info("GET /api/courses/enrolled/{} - Fetching enrolled courses for student", userid);
		List<EnrolledCourseDto> enrolledCourses = courseService.enrolledCoursesByStudent(userid);
		log.debug("Returning {} enrolled courses for user ID {}", enrolledCourses.size(), userid);
		return ResponseEntity.status(HttpStatus.OK).body(enrolledCourses);
	}
	
	@GetMapping("/findTop5Courses")
	public ResponseEntity<List<Top5CoursesDto>> findTop5Courses(){
		List<Top5CoursesDto> top5Courses =courseService.findTop5Courses();
		return ResponseEntity.status(HttpStatus.OK).body(top5Courses);
	}

}
