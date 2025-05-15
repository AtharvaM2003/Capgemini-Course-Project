package com.capgemini.courseproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.courseproject.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	boolean existsByUserName(String userName);
	
	Optional<User> findByEmail(String Email);

}
