package com.capgemini.courseproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.courseproject.entities.Instructor;
import com.capgemini.courseproject.exceptions.InstructorNotFoundException;
import com.capgemini.courseproject.repositories.InstructorRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InstructorServiceImpl implements InstructorService {

	private final InstructorRepository instructorRepo;

	@Autowired
	public InstructorServiceImpl(InstructorRepository instructorRepo) {
		super();
		this.instructorRepo = instructorRepo;
	}

	@Override
	public List<Instructor> findAllInstructor() {
		log.debug("Fetching all instructors from the repository");
		return instructorRepo.findAll();
	}

	@Override
	public Instructor findInstructorById(Long instructorId) {
		log.debug("Fetching instructor by ID: {}", instructorId);
		return instructorRepo.findById(instructorId).orElseThrow(() -> {
			log.warn("Instructor not found with ID: {}", instructorId);
			return new InstructorNotFoundException("Instructor not found with instructorId" + instructorId);
		});

	}

	@Override
	public Instructor createInstructor(Instructor instructor) {
		log.debug("Saving new instructor to the repository");
		return instructorRepo.save(instructor);
	}

	@Override
	public Instructor updateInstructor(Instructor updated, Long instructorId) {
		Instructor existing = instructorRepo.findById(instructorId).orElseThrow(() -> {
			log.warn("Instructor not found with ID: {}", instructorId);
			return new InstructorNotFoundException("Instructor not found with instructorId" + instructorId);
		});
		log.debug("Existing instructor data: {}", existing);
		existing.setName(updated.getName());
		existing.setExpertise(updated.getExpertise());

		Instructor saved = instructorRepo.save(existing);
		log.debug("Instructor updated successfully: {}", saved);

		return saved;
	}

	@Override
	public boolean deleteInstructor(Long instructorId) {
		if (!instructorRepo.existsById(instructorId)) {
			throw new InstructorNotFoundException("Instructor not found with instructorId" + instructorId);

		}
		log.debug("Deleting instructor by ID: {}", instructorId);
		instructorRepo.deleteById(instructorId);
		return false;
	}

}
