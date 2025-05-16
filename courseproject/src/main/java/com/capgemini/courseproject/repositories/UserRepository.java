package com.capgemini.courseproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capgemini.courseproject.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	boolean existsByUserName(String userName);
	
	Optional<User> findByEmail(String email);
	
	@Query("SELECT u.userName FROM User u WHERE u.userId = :userId")
	String findUserNameById(@Param("userId") Long userId);

}
