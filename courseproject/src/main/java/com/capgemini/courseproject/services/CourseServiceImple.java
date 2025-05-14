package com.capgemini.courseproject.services;

import com.capgemini.courseproject.entities.Course;
import com.capgemini.courseproject.repositories.CourseRepository;
import com.capgemini.courseproject.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImple implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long courseId, Course updatedCourse) {
        Optional<Course> existingCourseOpt = courseRepository.findById(courseId);
        if (existingCourseOpt.isPresent()) {
            Course existingCourse = existingCourseOpt.get();
            existingCourse.setTitle(updatedCourse.getTitle());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setInstructor(updatedCourse.getInstructor());
            // Note: You may or may not want to update enrollments/assignments directly here.
            return courseRepository.save(existingCourse);
        }
        return null;
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }
}
