package com.capgemini.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.courseproject.entities.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {

	long count();
}
