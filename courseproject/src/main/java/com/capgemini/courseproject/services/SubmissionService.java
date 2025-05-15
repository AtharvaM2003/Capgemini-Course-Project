package com.capgemini.courseproject.services;

import java.util.List;

import com.capgemini.courseproject.entities.Submission;

public interface SubmissionService {

	List<Submission> findAllsubmissions();

	Submission findSubmissionById(Long submissionId);

}
