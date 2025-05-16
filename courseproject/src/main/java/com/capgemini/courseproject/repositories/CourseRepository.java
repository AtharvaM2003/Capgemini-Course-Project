package com.capgemini.courseproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.capgemini.courseproject.dto.CourseDto;
import com.capgemini.courseproject.dto.CourseEnrollmentDto;
import com.capgemini.courseproject.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	@Query("SELECT new com.capgemini.courseproject.dto.CourseEnrollmentDto(" + "c.courseId, c.title, i.name, COUNT(e)) "
			+ "FROM Course c " + "JOIN c.instructor i " + "LEFT JOIN c.enrollments e "
			+ "GROUP BY c.courseId, c.title, i.name")
	List<CourseEnrollmentDto> getCourseEnrollmentReport();

	@Query("SELECT c.title FROM Course c WHERE c.instructor.instructorId = ?1")
	List<String> findCourseTitlesByInstructorId(Long instructorId);

	@Query("SELECT c.title FROM Course c WHERE c.courseId = :courseId")
	String findCourseTitleById(@Param("courseId") Long courseId);

	
    @Query("SELECT new com.capgemini.courseproject.dto.CourseDto("
            + "c.id, c.title, c.description, c.instructor.id, c.instructor.name, c.fees) "
            + "FROM Course c")
	List<CourseDto> getAllCourses();


}
