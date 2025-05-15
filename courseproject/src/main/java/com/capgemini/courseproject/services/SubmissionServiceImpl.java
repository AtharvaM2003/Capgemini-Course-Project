package com.capgemini.courseproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.capgemini.courseproject.entities.Submission;
import com.capgemini.courseproject.exceptions.SubmissionNotFoundException;
import com.capgemini.courseproject.repositories.SubmissionRepository;

@Service
@Slf4j
public class SubmissionServiceImpl implements SubmissionService {
	
	private final SubmissionRepository repository;

	@Autowired
	public SubmissionServiceImpl(SubmissionRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Submission> findAllsubmissions() {
		log.debug("Fetching all submissions");
		return repository.findAll();
	}

	@Override
	public Submission findSubmissionById(Long submissionId) {
		log.debug("Fetching submission by ID: {}", submissionId);
		return repository.findById(submissionId)
				.orElseThrow(() -> {
					log.warn("Submission not found with ID: {}", submissionId);
					return new SubmissionNotFoundException("Submission not found with ID: " + submissionId);
				});
	}
	
	@Override
	public List<Submission> findSubmissionsByUserId(Long userId) {
		log.debug("Fetching submissions for user ID: {}", userId);
		List<Submission> submissions = repository.findByUserUserId(userId);
		if (submissions.isEmpty()) {
			log.warn("No submissions found for user ID: {}", userId);
		}
		return submissions;
	}


	@Override
	public List<Submission> findSubmissionsByAssignmentId(Long assignmentId) {
	    log.debug("Fetching submissions for assignment ID: {}", assignmentId);
	    List<Submission> submissions = repository.findByAssignmentId(assignmentId);
	    if (submissions.isEmpty()) {
	        log.warn("No submissions found for assignment ID: {}", assignmentId);
	    }
	    return submissions;
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
