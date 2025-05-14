package com.capgemini.courseproject.services;

import java.util.List;

import com.capgemini.courseproject.entities.Instructor;

public interface InstructorService {
	List<Instructor> findAllInstructor();

	Instructor findInstructorById(Long instructorId);

	Instructor createInstructor(Instructor instructor);

	Instructor updateInstructor(Instructor instructor, Long instructorId);

	boolean deleteInstructor(Long instructorId);
}
