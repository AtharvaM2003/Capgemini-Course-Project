package com.capgemini.courseproject.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.courseproject.entities.Enrollment;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	@Override
	public Enrollment addEnrollment(Enrollment enrollment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enrollment deleteEnrollment(Long enrollmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enrollment updateEnrollment(Long enrollmentId, Enrollment updatedEnrollment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enrollment> getAllEnrollments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Enrollment> getEnrollmentById(Long enrollmentId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Enrollment> getEnrollmentByDate(LocalDate enrollmentDate) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	

}
