package com.capgemini.courseproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.courseproject.entities.Instructor;
import com.capgemini.courseproject.exceptions.InstructorNotFoundException;
import com.capgemini.courseproject.repositories.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService {

	private final InstructorRepository instructorRepo;

	@Autowired
	public InstructorServiceImpl(InstructorRepository instructorRepo) {
		super();
		this.instructorRepo = instructorRepo;
	}

	@Override
	public List<Instructor> findAllInstructor() {

		return instructorRepo.findAll();
	}

	@Override
	public Instructor findInstructorById(Long instructorId) {

		return instructorRepo.findById(instructorId).orElseThrow(
				() -> new InstructorNotFoundException("Instructor not found with instructorId" + instructorId));
	}

	@Override
	public Instructor createInstructor(Instructor instructor) {
		return instructorRepo.save(instructor);
	}

	@Override
	public Instructor updateInstructor(Instructor updated, Long instructorId) {
		Instructor existing = instructorRepo.findById(instructorId).orElseThrow(
				() -> new InstructorNotFoundException("Instructor not found with instructorId" + instructorId));
		existing.setName(updated.getName());
		existing.setExpertise(updated.getExpertise());
		return instructorRepo.save(existing);
	}

	@Override
	public boolean deleteInstructor(Long instructorId) {
		if (!instructorRepo.existsById(instructorId)) {
			throw new InstructorNotFoundException("Instructor not found with instructorId" + instructorId);

		}
		instructorRepo.deleteById(instructorId);
		return false;
	}

}
