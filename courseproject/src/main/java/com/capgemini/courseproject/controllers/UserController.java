package com.capgemini.courseproject.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.capgemini.courseproject.dto.StudentDto;
import com.capgemini.courseproject.entities.User;
import com.capgemini.courseproject.services.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUsers() {
		log.info("GET /api/users - Request received to fetch all users");
		List<User> userList = userService.findAllUser();
		log.debug("Returning {} users", userList.size());
		return ResponseEntity.status(HttpStatus.OK).body(userList);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		log.info("GET /api/users/{} - Request received to fetch user by ID", id);
		User user = userService.findUserById(id);
		log.debug("Fetched User: {}", user);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<List<StudentDto>> fetchStudentOfCourses(@PathVariable Long id) {
		List<StudentDto> students = userService.fetchStudentOfCourses(id);
		return ResponseEntity.status(HttpStatus.OK).body(students);

	}

	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> fetchAllStudentsNames() {
		List<StudentDto> students = userService.fetchAllStudentsNames();
		return ResponseEntity.status(HttpStatus.OK).body(students);

	}

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result) {
		log.info("POST /api/users - Request received to create user: {}", user);
		if (result.hasErrors()) {
			log.error("Validation failed for user creation: {}", result.getAllErrors());
			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		User savedUser = userService.createUser(user);
		log.debug("User created with ID: {}", savedUser.getUserId());
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User newUser,
			BindingResult result) {
		log.info("PUT /api/users/{} - Request received to update user: {}", id, newUser);
		if (result.hasErrors()) {
			log.error("Validation failed for user creation: {}", result.getAllErrors());
			throw new IllegalArgumentException(result.getFieldErrors().toString());
		}
		User updatedUser = userService.updateUser(id, newUser);
		log.debug("User with ID {} updated: {}", id, updatedUser);
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		log.info("DELETE /api/users/{} - Request received to delete user", id);
		userService.deleteUser(id);
		log.info("User with ID {} successfully deleted", id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@GetMapping("/allStudents")
    public ResponseEntity<List<User>> getAllStudents() {
		log.info("GET /api/allStudents - Request received to fetch all student");
        List<User> students = userService.findAllStudents();
        log.info("GET /api/allStudents - Request received to fetch all students");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}
