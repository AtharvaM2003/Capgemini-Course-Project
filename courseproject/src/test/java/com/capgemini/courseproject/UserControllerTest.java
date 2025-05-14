package com.capgemini.courseproject;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.capgemini.courseproject.controllers.UserController;
import com.capgemini.courseproject.entities.User;
import com.capgemini.courseproject.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindUserById() {
		Long userId = 1L;
		User mockUser = new User(userId, "Alice", "alice@gmail.com", "pass123", "9876543210", "student", null, null);

		when(userService.findUserById(userId)).thenReturn(mockUser);

		ResponseEntity<User> response = userController.getUser(userId);

		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getUserId()).isEqualTo(userId);
		assertThat(response.getBody().getName()).isEqualTo("Alice");
		assertThat(response.getBody().getEmail()).isEqualTo("alice@gmail.com");
	}

	@Test
	public void testCreateUser() {
		User inputUser = new User(null, "Bob", "bob@gmail.com", "pass456", "9876500000", "student", null, null);
		User savedUser = new User(2L, "Bob", "bob@gmail.com", "pass456", "9876500000", "student", null, null);

		when(userService.createUser(any(User.class))).thenReturn(savedUser);

		ResponseEntity<User> response = userController.createUser(inputUser);

		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getUserId()).isEqualTo(2L);
		assertThat(response.getBody().getName()).isEqualTo("Bob");
	}
}
