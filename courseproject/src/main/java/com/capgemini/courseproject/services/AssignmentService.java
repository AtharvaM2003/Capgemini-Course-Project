package com.capgemini.courseproject.services;

import com.capgemini.courseproject.entities.Assignment;
import com.capgemini.courseproject.entities.Course;
import java.util.List;
import java.util.Optional;

public interface AssignmentService {
	Assignment createAssignment(Assignment assignment);
    
    Optional<Assignment> getAssignmentById(Long assignmentId);
    
    List<Assignment> getAllAssignments();
    

}

