package com.capgemini.courseproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.courseproject.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	boolean existsByUserName(String userName);
	
	Optional<User> findByEmail(String email);
	
	@Query("Select u FROM User u where u.userType='USER'")
	List<User> findAllStudents();

}
