package com.capgemini.courseproject.services;

import com.capgemini.courseproject.dto.AvailableCourseDto;
import com.capgemini.courseproject.dto.CourseDto;
import com.capgemini.courseproject.dto.CourseEnrollmentDto;
import com.capgemini.courseproject.dto.EnrolledCourseDto;
import com.capgemini.courseproject.entities.Assignment;
import com.capgemini.courseproject.entities.Course;
import com.capgemini.courseproject.exceptions.CourseNotFoundException;
import com.capgemini.courseproject.repositories.AssignmentRepository;
import com.capgemini.courseproject.repositories.CourseRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseServiceImple implements CourseService {

	CourseRepository courseRepository;
	AssignmentRepository assignmentRepository;

	@Autowired
	public CourseServiceImple(CourseRepository courseRepository, AssignmentRepository assignmentRepository) {
		this.courseRepository = courseRepository;
		this.assignmentRepository = assignmentRepository;
	}

	@Override
	public Course addCourse(Course course) {
		log.debug("Saving new course to the repository");
		return courseRepository.save(course);
	}

	@Override
	public Course updateCourse(Long courseId, Course updatedCourse) {
		Course existing = courseRepository.findById(courseId).orElseThrow(() -> {
			log.warn("Course not found with ID: {}", courseId);
			return new CourseNotFoundException("Course not found with courseId" + courseId);
		});
		log.debug("Existing course data: {}", existing);
		Optional<Course> existingCourseOpt = courseRepository.findById(courseId);
		if (existingCourseOpt.isPresent()) {
			Course existingCourse = existingCourseOpt.get();
			existingCourse.setTitle(updatedCourse.getTitle());
			existingCourse.setDescription(updatedCourse.getDescription());
			existingCourse.setInstructor(updatedCourse.getInstructor());
			existingCourse.setFees(updatedCourse.getFees());

			log.debug("Course updated successfully: {}", existingCourse);
			return courseRepository.save(existingCourse);
		}
		return null;
	}

	@Override
	public void deleteCourse(Long courseId) {
		if (!courseRepository.existsById(courseId)) {
			throw new CourseNotFoundException("Course not found with icourseId" + courseId);

		}
		log.debug("Deleting course by ID: {}", courseId);
		courseRepository.deleteById(courseId);
	}

	@Override
	public List<CourseDto> getAllCourses() {
		log.debug("Fetching all courses from repository");
		return courseRepository.getAllCourses();
	}

	@Override
	public Optional<Course> getCourseById(Long courseId) {
		log.debug("Fetching course by ID : {}", courseId);

		if (!courseRepository.existsById(courseId)) {
			throw new CourseNotFoundException("Course not found with icourseId" + courseId);
		}
		return courseRepository.findById(courseId);
	}

	@Override
	public List<Assignment> findByCourseCourseId(Long courseId) {
		List<Assignment> assignments = assignmentRepository.findByCourse_CourseId(courseId);
		log.debug("Found {} assignments for course ID: {}", assignments.size(), courseId);
		return assignments;
	}

	@Override
	public List<CourseEnrollmentDto> getCourseEnrollmentReport() {

		List<CourseEnrollmentDto> report = courseRepository.getCourseEnrollmentReport();
		log.debug("Retrieved {} course enrollment records", report.size());
		return report;
	}

	@Override
	public List<String> getCourseTitlesByInstructorId(Long instructorId) {
		List<String> titles = courseRepository.findCourseTitlesByInstructorId(instructorId);
		log.debug("Found {} course titles for instructor ID: {}", titles.size(), instructorId);
		return titles;
	}

	@Override
	public List<AvailableCourseDto> findCoursesWithIsEnrollment(Long userId) {
		List<AvailableCourseDto> availableCourses = courseRepository.findCoursesWithIsEnrollment(userId);
		log.debug("Found {} available courses for user ID: {}", availableCourses.size(), userId);
		return availableCourses;
	}

	@Override
	public List<EnrolledCourseDto> enrolledCoursesByStudent(Long studentId) {

		List<EnrolledCourseDto> enrolledCourses = courseRepository.enrolledCoursesByStudent(studentId);
		log.debug("Found {} enrolled courses for student ID: {}", enrolledCourses.size(), studentId);
		return enrolledCourses;
	}

}
