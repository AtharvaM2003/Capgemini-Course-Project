package com.capgemini.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.courseproject.entities.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long>{
 
}
