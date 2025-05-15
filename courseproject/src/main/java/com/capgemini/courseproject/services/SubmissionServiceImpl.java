package com.capgemini.courseproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.courseproject.entities.Submission;
import com.capgemini.courseproject.exceptions.SubmissionNotFoundException;
import com.capgemini.courseproject.repositories.SubmissionRepository;

@Service
public class SubmissionServiceImpl implements SubmissionService {
	
	private final SubmissionRepository repository;

	@Autowired
	public SubmissionServiceImpl(SubmissionRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Submission> findAllsubmissions() {
		return repository.findAll();
	}

	@Override
	public Submission findSubmissionById(Long submissionId) {
		return repository.findById(submissionId)
				.orElseThrow(() -> new SubmissionNotFoundException("Submission not found with ID: " + submissionId));
	}

//	@Override
//	public boolean deleteSubmission(Long submissionId) {
//		if (repository.existsById(submissionId)) {
//			repository.deleteById(submissionId);
//			return true;
//		}
//		return false;
//	}

}
