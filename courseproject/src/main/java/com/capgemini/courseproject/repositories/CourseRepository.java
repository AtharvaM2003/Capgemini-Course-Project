package com.capgemini.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.courseproject.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	@Query("SELECT c.title FROM Course c WHERE c.courseId = :courseId")
	String findCourseTitleById(@Param("courseId") Long courseId);

}
