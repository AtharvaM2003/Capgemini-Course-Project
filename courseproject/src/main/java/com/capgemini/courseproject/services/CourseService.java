package com.capgemini.courseproject.services;

import com.capgemini.courseproject.entities.Course;
import java.util.List;
import java.util.Optional;

public interface CourseService {
	
    Course addCourse(Course course);
    
    Course updateCourse(Long courseId, Course updatedCourse);
    
    void deleteCourse(Long courseId);
    
    List<Course> getAllCourses();
    
    Optional<Course> getCourseById(Long courseId);
}
