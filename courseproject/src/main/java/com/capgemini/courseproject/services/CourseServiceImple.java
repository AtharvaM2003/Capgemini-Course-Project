package com.capgemini.courseproject.services;

import com.capgemini.courseproject.dto.CourseDto;
import com.capgemini.courseproject.dto.CourseEnrollmentDto;
import com.capgemini.courseproject.dto.Top5CoursesDto;
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
		return assignmentRepository.findByCourse_CourseId(courseId);
	}

	@Override
	public List<CourseEnrollmentDto> getCourseEnrollmentReport() {

		return courseRepository.getCourseEnrollmentReport();
	}

	@Override
	public List<String> getCourseTitlesByInstructorId(Long instructorId) {
		return courseRepository.findCourseTitlesByInstructorId(instructorId);
	}
	
	@Override
	public List<Top5CoursesDto> findTop5Courses() {
		return courseRepository.findTop5Courses();
	}

}
