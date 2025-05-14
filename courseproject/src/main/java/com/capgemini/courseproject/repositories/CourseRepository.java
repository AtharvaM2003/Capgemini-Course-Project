package com.capgemini.courseproject.repositories;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import com.capgemini.courseproject.dto.AvailableCourseDto;
//import com.capgemini.courseproject.dto.CourseDto;
//import com.capgemini.courseproject.dto.CourseEnrollmentReportDto;
//import com.example.course.dto.EnrolledCourseDto;
//import com.example.course.dto.Top5CoursesDto;
import com.capgemini.courseproject.entities.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
//	@Query("SELECT new com.example.course.dto.CourseEnrollmentReportDto(" +
//		       "c.id, c.title, i.name, COUNT(e)) " +
//		       "FROM Course c " +
//		       "JOIN Instructor i ON c.instructorid = i.id " +
//		       "LEFT JOIN Enrollment e ON e.courseid = c.id " +
//		       "GROUP BY c.id, c.title, i.name")
//		List<CourseEnrollmentReportDto> getCourseEnrollmentReport();
//	
//	@Query("SELECT new com.example.course.dto.CourseDto"
//			+ "(c.id, c.title,c.description,c.instructorid,i.name,c.fees)"
//			+ "FROM Course c JOIN Instructor i ON c.instructorid=i.id")
//	List<CourseDto> findAllCourses();
//	
//	
////Available Courses
//	@Query("SELECT new com.example.course.dto.AvailableCourseDto(" +
//		       "c.id, c.title, c.description, c.instructorid, i.name, c.fees, " +
//		       "CASE WHEN e.id IS NOT NULL THEN true ELSE false END) " +
//		       "FROM Course c " +
//		       "JOIN Instructor i ON c.instructorid = i.id " +
//		       "LEFT JOIN Enrollment e ON e.courseid = c.id AND e.userid = :userId")
//	List<AvailableCourseDto> findCoursesWithIsEnrollment(@Param("userId") Long userId);
//
//	
//	@Query("SELECT new com.example.course.dto.EnrolledCourseDto(c.title, i.name, e.enrollmentdate, c.fees) " +
//		       "FROM Course c " +
//		       "JOIN Enrollment e ON c.id = e.courseid " +
//		       "JOIN Instructor i ON i.id = c.instructorid " +
//		       "JOIN User u ON u.id = e.userid " +
//		       "WHERE u.id = ?1")
//		List<EnrolledCourseDto> enrolledCoursesByStudent(Long studentId);
//
//	@Query(value = """
//		    SELECT c.title AS courseTitle, COUNT(e.courseid) AS totalEnrollments
//		    FROM enrollment e
//		    JOIN course c ON e.courseid = c.id
//		    GROUP BY c.title
//		    ORDER BY totalEnrollments DESC
//		    LIMIT 5
//		""", nativeQuery = true)
//		List<Top5CoursesDto> findTop5Courses();

}
