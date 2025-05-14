package com.capgemini.courseproject.services;



import java.util.List;
import java.util.Optional;

import com.capgemini.courseproject.entities.Enrollment;



public interface EnrollmentService {
	
	Enrollment addEnrollment(Enrollment enrollment);
	
//	void deleteEnrollment(Long enrollmentId);
	
//	Enrollment updateEnrollment(Long enrollmentId, Enrollment updatedEnrollment);
	
	List<Enrollment> getAllEnrollments();
	
	Optional<Enrollment> getEnrollmentById(Long enrollmentId);
	
	
	

}
