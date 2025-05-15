package com.capgemini.courseproject.controllers;

import java.net.URI;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.capgemini.courseproject.entities.User;
import com.capgemini.courseproject.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userService.findAllUser();
		return ResponseEntity.status(HttpStatus.OK).body(userList);

	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		User user = userService.findUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);

	}

	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User saved = userService.createUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).location(URI.create("/api/users/" + saved.getUserId()))
				.body(saved);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User newUser) {
		User user = userService.updateUser(id, newUser);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
