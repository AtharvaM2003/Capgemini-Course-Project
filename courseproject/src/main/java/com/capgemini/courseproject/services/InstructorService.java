package com.capgemini.courseproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.courseproject.dto.InstructorWiseStudentDto;
import com.capgemini.courseproject.entities.Instructor;
import com.capgemini.courseproject.repositories.InstructorRepository;

public interface InstructorService {

	List<Instructor> findAllInstructor();

	Instructor findInstructorById(Long instructorId);

	Instructor createInstructor(Instructor instructor);

	Instructor updateInstructor(Instructor instructor, Long instructorId);

	boolean deleteInstructor(Long instructorId);
	
	List<InstructorWiseStudentDto> getInstructorWiseStudentCount();
}
