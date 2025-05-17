package com.capgemini.courseproject.services;

import com.capgemini.courseproject.dto.AssignmentDto;
import com.capgemini.courseproject.entities.Assignment;
import com.capgemini.courseproject.entities.Course;
import com.capgemini.courseproject.exceptions.AssignmentNotFoundException;
import com.capgemini.courseproject.repositories.AssignmentRepository;
import com.capgemini.courseproject.repositories.CourseRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AssignmentServiceImpl implements AssignmentService {

	private AssignmentRepository assignmentRepository;
	private CourseRepository courseRepository;

	@Autowired
	public void setAssignmentService(AssignmentRepository assignmentRepository, CourseRepository courseRepository) {
		this.assignmentRepository = assignmentRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	public AssignmentDto addAssignment(AssignmentDto dto) {
		Course course = courseRepository.findById(dto.getCourseId())
				.orElseThrow(() -> new RuntimeException("Course not found"));

		Assignment assignment = new Assignment();
		assignment.setTitle(dto.getTitle());
		assignment.setDescription(dto.getDescription());
		assignment.setCourse(course);

		Assignment savedAssignment = assignmentRepository.save(assignment);

		AssignmentDto responseDto = new AssignmentDto(savedAssignment.getAssignmentId(), savedAssignment.getTitle(),
				savedAssignment.getDescription(), course.getCourseId(), course.getTitle());

		return responseDto;
	}

	@Override
	public Optional<Assignment> getAssignmentById(Long id) {
		log.debug("Fetching assignment by ID: {}", id);
		return assignmentRepository.findById(id);
	}

	@Override
	public List<AssignmentDto> getAllAssignments() {
		return assignmentRepository.findAllAssignmentDtos();
	}

	@Override
	public Assignment updateAssignment(Long assignmentId, Assignment updatedAssignment) {
		Assignment existing = assignmentRepository.findById(assignmentId).orElseThrow(() -> {
			log.warn("Assignment not found with ID: {}", assignmentId);
			return new AssignmentNotFoundException("Course not found with courseId" + assignmentId);
		});
		log.debug("Existing course data: {}", existing);
		Optional<Assignment> existingAssignmentOpt = assignmentRepository.findById(assignmentId);
		if (existingAssignmentOpt.isPresent()) {
			Assignment existingAssignment = existingAssignmentOpt.get();
			existingAssignment.setTitle(updatedAssignment.getTitle());
			existingAssignment.setDescription(updatedAssignment.getDescription());
//			existingAssignment.setSubmissions(updatedAssignment.getSubmissions());
//			existingAssignment.setCourse(updatedAssignment.getCourse());
//			existingAssignment.getSubmissions().clear();
//			existingAssignment.getSubmissions().addAll(updatedAssignment.getSubmissions());
//			log.debug("Assignment updated successfully: {}", existingAssignment);
			return assignmentRepository.save(existingAssignment);
		}
		return null;
	}
}
