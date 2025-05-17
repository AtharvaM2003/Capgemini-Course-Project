package com.capgemini.courseproject.services;

import com.capgemini.courseproject.dto.AvailableCourseDto;
import com.capgemini.courseproject.dto.CourseDto;
import com.capgemini.courseproject.dto.CourseEnrollmentDto;

import com.capgemini.courseproject.dto.Top5CoursesDto;

import com.capgemini.courseproject.dto.EnrolledCourseDto;

import com.capgemini.courseproject.entities.Assignment;
import com.capgemini.courseproject.entities.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {

	List<CourseDto> getAllCourses();

	Course addCourse(Course course);

	Course updateCourse(Long courseId, Course updatedCourse);

	void deleteCourse(Long courseId);

	Optional<Course> getCourseById(Long courseId);

	List<Assignment> findByCourseCourseId(Long courseId);

	List<CourseEnrollmentDto> getCourseEnrollmentReport();

	List<String> getCourseTitlesByInstructorId(Long instructorId);


	List<Top5CoursesDto> findTop5Courses(); 
	

	public List<AvailableCourseDto> findCoursesWithIsEnrollment(Long userId);

	public List<EnrolledCourseDto> enrolledCoursesByStudent(Long studentId);


}
