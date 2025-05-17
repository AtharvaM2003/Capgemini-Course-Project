package com.capgemini.courseproject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.capgemini.courseproject.dto.AvailableCourseDto;
import com.capgemini.courseproject.dto.CourseDto;
import com.capgemini.courseproject.dto.CourseEnrollmentDto;

import com.capgemini.courseproject.dto.Top5CoursesDto;

import com.capgemini.courseproject.dto.EnrolledCourseDto;

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
			+ "c.courseId, c.title, c.description, c.instructor.instructorId, c.instructor.name, c.fees) "
			+ "FROM Course c")
	List<CourseDto> getAllCourses();


//    long count();
    
    @Query(value = """
		    SELECT c.title AS courseTitle, COUNT(e.courseid) AS totalEnrollments
		    FROM enrollment e
		    JOIN course c ON e.courseid = c.id
		    GROUP BY c.title
		    ORDER BY totalEnrollments DESC
		    LIMIT 5
		""", nativeQuery = true)
		List<Top5CoursesDto> findTop5Courses(); 
    
	long count();

	@Query("SELECT new com.capgemini.courseproject.dto.AvailableCourseDto("
			+ "c.courseId, c.title, c.description, c.instructor.instructorId, c.instructor.name, c.fees, "
			+ "CASE WHEN e.enrollmentId IS NOT NULL THEN true ELSE false END) " + "FROM Course c "
			+ "LEFT JOIN Enrollment e ON e.course.courseId = c.courseId AND e.user.userId = :userId")
	List<AvailableCourseDto> findCoursesWithIsEnrollment(@Param("userId") Long userId);

	@Query("SELECT new com.capgemini.courseproject.dto.EnrolledCourseDto(c.title, i.name, e.enrollmentDate, c.fees) "
			+ "FROM Course c " + "JOIN Enrollment e ON c.courseId = e.course.courseId "
			+ "JOIN Instructor i ON i.instructorId = c.instructor.instructorId " + "JOIN User u ON u.userId = e.user.userId "
			+ "WHERE u.userId = ?1")
	List<EnrolledCourseDto> enrolledCoursesByStudent(Long studentId);


}
