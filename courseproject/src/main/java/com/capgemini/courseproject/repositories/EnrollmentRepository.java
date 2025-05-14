package com.capgemini.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.courseproject.entities.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>{

}
