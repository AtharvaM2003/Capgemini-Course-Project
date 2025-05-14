package com.capgemini.courseproject.services;

import com.capgemini.courseproject.entities.Assignment;
import com.capgemini.courseproject.entities.Course;
import java.util.List;
import java.util.Optional;

public interface AssignmentService {
	Assignment createAssignment(Assignment assignment);
	
    Assignment updateAssignment(Long assignmentId, Assignment assignment);
    
    void deleteAssignment(Long assignmentId);
    
    Optional<Assignment> getAssignmentById(Long assignmentId);
    
    List<Assignment> getAllAssignments();
    

}

