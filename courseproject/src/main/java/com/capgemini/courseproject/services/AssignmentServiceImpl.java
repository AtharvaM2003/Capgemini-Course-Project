package com.capgemini.courseproject.services;

import com.capgemini.courseproject.entities.Assignment;
import com.capgemini.courseproject.repositories.AssignmentRepository;
import com.capgemini.courseproject.services.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    @Autowired
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public Assignment createAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public Assignment updateAssignment(Long id, Assignment assignment) {
        Optional<Assignment> existingAssignment = assignmentRepository.findById(id);
        if (existingAssignment.isPresent()) {
            Assignment updated = existingAssignment.get();
            updated.setCourse(assignment.getCourse());
            updated.setTitle(assignment.getTitle());
            updated.setDescription(assignment.getDescription());
            return assignmentRepository.save(updated);
        } else {
            throw new RuntimeException("Assignment not found with ID: " + id);
        }
    }

    @Override
    public void deleteAssignment(Long id) {
        if (!assignmentRepository.existsById(id)) {
            throw new RuntimeException("Assignment not found with ID: " + id);
        }
        assignmentRepository.deleteById(id);
    }

    @Override
    public Optional<Assignment> getAssignmentById(Long id) {
        return assignmentRepository.findById(id);
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    
}

