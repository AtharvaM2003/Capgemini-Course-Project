package com.capgemini.courseproject.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.capgemini.courseproject.dto.CourseInfoDto;

import com.capgemini.courseproject.dto.EnrollmentReportDTO;
import com.capgemini.courseproject.entities.Course;

import com.capgemini.courseproject.entities.Enrollment;
import com.capgemini.courseproject.entities.User;
import com.capgemini.courseproject.repositories.CourseRepository;
import com.capgemini.courseproject.repositories.EnrollmentRepository;
import com.capgemini.courseproject.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EnrollmentServiceImpl implements EnrollmentService {

	EnrollmentRepository enrollmentRepository;
	
	CourseRepository courseRepository;
	
	UserRepository userRepository;
	
	@Autowired
	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository,
			UserRepository userRepository) {
		super();
		this.enrollmentRepository = enrollmentRepository;
		this.courseRepository = courseRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Enrollment addEnrollment(Enrollment enrollment) {

		log.debug("Saving new Enrollment to Repository");

		return enrollmentRepository.save(enrollment);
	}

	@Override
	public List<Enrollment> getAllEnrollments() {
		log.debug("Fetching all enrollments");

		return enrollmentRepository.findAll();
	}

	@Override
	public Optional<Enrollment> getEnrollmentById(Long enrollmentId) {

		log.debug("Fetching enrollments by ID:{}", enrollmentId);
		return enrollmentRepository.findById(enrollmentId);

	}
	
	@Override
	public List<EnrollmentReportDTO> getEnrollmentReport() {
	    List<Enrollment> enrollments = enrollmentRepository.findAll();

	    return enrollments.stream().map(enroll -> {
	        String courseTitle = "Unknown Course";
	        String studentName = "Unknown Student";

	        if (enroll.getCourse() != null) {
	            courseTitle = courseRepository.findById(enroll.getCourse().getCourseId())
	                    .map(Course::getTitle)
	                    .orElse("Unknown Course");
	        }

	        if (enroll.getUser() != null) {
	            studentName = userRepository.findById(enroll.getUser().getUserId())
	                    .map(User::getUserName)
	                    .orElse("Unknown Student");
	        }

	        return new EnrollmentReportDTO(
	                enroll.getEnrollmentId(),
	                courseTitle,
	                studentName,
	                enroll.getEnrollmentDate()
	        );
	    }).collect(Collectors.toList());
	}

	@Override
	public List<CourseInfoDto> findCoursesByStudentId(Long studentId) {

		return enrollmentRepository.findCoursesByStudentId(studentId);
	}

}
