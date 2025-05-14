package com.capgemini.courseproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.courseproject.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

}
