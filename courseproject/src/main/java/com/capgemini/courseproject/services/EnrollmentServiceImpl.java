package com.capgemini.courseproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.courseproject.entities.Enrollment;

import com.capgemini.courseproject.repositories.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

	EnrollmentRepository enrollmentRepository;

	@Autowired
	public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
		this.enrollmentRepository = enrollmentRepository;
	}

	@Override
	public Enrollment addEnrollment(Enrollment enrollment) {
		
		return enrollmentRepository.save(enrollment);
	}

	/*
	 * @Override public void deleteEnrollment(Long enrollmentId) {
	 * if(!enrollmentRepository.existsById(enrollmentId)) { throw new
	 * EnrollmentNotFoundException("Enrollment with Id"+enrollmentId+"Not Found");
	 * 
	 * } enrollmentRepository.deleteById(enrollmentId);
	 * 
	 * }
	 */

	/*
	 * @Override public Enrollment updateEnrollment(Long enrollmentId, Enrollment
	 * updatedEnrollment) {
	 * 
	 * Optional<Enrollment> exists = enrollmentRepository.findById(enrollmentId);
	 * 
	 * if(exists.isPresent()) {
	 * 
	 * Enrollment list = exists.get();
	 * list.setCourseId(updatedEnrollment.getCourseId());
	 * list.setUserId(enrollmentId);
	 * 
	 * }
	 * 
	 * return null; }
	 */

	@Override
	public List<Enrollment> getAllEnrollments() {

		return enrollmentRepository.findAll();
	}

	@Override
	public Optional<Enrollment> getEnrollmentById(Long enrollmentId) {

		return enrollmentRepository.findById(enrollmentId);
	}

	


}
