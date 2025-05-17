package com.capgemini.courseproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.courseproject.dto.InstructorWiseStudentDto;
import com.capgemini.courseproject.entities.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

	long count();
	
	@Query("SELECT new com.capgemini.courseproject.dto.InstructorWiseStudentDto(COUNT(e.user.userI"
			+ "d), c.instructor.name) " +
		       "FROM Enrollment e " +
		       "JOIN e.course c " +
		       "GROUP BY c.instructor.name")
		List<InstructorWiseStudentDto> fetchInstructorWiseStudentCount();

}
