package com.capgemini.courseproject.services;

import java.util.List;
import java.util.Optional;

import com.capgemini.courseproject.dto.CourseInfoDto;

import com.capgemini.courseproject.dto.EnrollmentReportDTO;

import com.capgemini.courseproject.entities.Enrollment;

public interface EnrollmentService {

	Enrollment addEnrollment(Enrollment enrollment);

	List<Enrollment> getAllEnrollments();

	Optional<Enrollment> getEnrollmentById(Long enrollmentId);
    
	List<EnrollmentReportDTO> getEnrollmentReport();

	List<CourseInfoDto> findCoursesByStudentId(Long studentId);

}
