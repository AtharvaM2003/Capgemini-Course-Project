package com.capgemini.courseproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.courseproject.dto.CourseInfoDto;
import com.capgemini.courseproject.entities.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
	@Query("SELECT new com.capgemini.courseproject.dto.CourseInfoDto(" + "e.course.title, e.course.description) "
			+ "FROM Enrollment e " + "WHERE e.user.userId = :studentId")
	List<CourseInfoDto> findCoursesByStudentId(@Param("studentId") Long studentId);

	
	long count();
}
