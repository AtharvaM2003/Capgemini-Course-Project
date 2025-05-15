package com.capgemini.courseproject.services;

import com.capgemini.courseproject.entities.Assignment;
import com.capgemini.courseproject.repositories.AssignmentRepository;
import com.capgemini.courseproject.services.AssignmentService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public Assignment createAssignment(Assignment assignment) {
    	log.debug("Creating new Assignment to Repository");
        return assignmentRepository.save(assignment);
    }

    @Override
    public Optional<Assignment> getAssignmentById(Long id) {
    	log.debug("Fetching assignment by ID: {}", id); 
        return assignmentRepository.findById(id);
    }

    @Override
    public List<Assignment> getAllAssignments() {
    	log.debug("Fetching all assignments from the repository"); 
        return assignmentRepository.findAll();
    }

    
}

