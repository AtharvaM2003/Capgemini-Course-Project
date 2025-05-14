package com.capgemini.courseproject.services;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.capgemini.courseproject.entities.Enrollment;



public interface EnrollmentService {
	
	Enrollment addEnrollment(Enrollment enrollment);
	
	Enrollment deleteEnrollment(Long enrollmentId);
	
	Enrollment updateEnrollment(Long enrollmentId, Enrollment updatedEnrollment);
	
	List<Enrollment> getAllEnrollments();
	
	Optional<Enrollment> getEnrollmentById(Long enrollmentId);
	
	Optional<Enrollment> getEnrollmentByDate(LocalDate enrollmentDate);
	

}
