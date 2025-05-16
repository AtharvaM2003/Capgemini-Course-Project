package com.capgemini.courseproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.courseproject.dto.CourseEnrollmentDto;
import com.capgemini.courseproject.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	@Query("SELECT new com.capgemini.courseproject.dto.CourseEnrollmentDto(" + "c.courseId, c.title, i.name, COUNT(e)) "
			+ "FROM Course c " + "JOIN c.instructor i " + "LEFT JOIN c.enrollments e "
			+ "GROUP BY c.courseId, c.title, i.name")
	List<CourseEnrollmentDto> getCourseEnrollmentReport();

}
