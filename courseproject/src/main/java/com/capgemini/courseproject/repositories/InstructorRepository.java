package com.capgemini.courseproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.courseproject.entities.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

//	@Query("SELECT c.title FROM Course c where c.instructor_id=?1")
//	List<String> fetchInstructorCourses(Long id);
}
