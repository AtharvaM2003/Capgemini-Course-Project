package com.capgemini.courseproject;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import com.capgemini.courseproject.controllers.UserController;
import com.capgemini.courseproject.entities.User;
import com.capgemini.courseproject.services.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

class UserControllerTest {

	@Mock
	private UserService userService;

	@Mock
	private BindingResult bindingResult;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetUserById() {
		Long userId = 1L;
		User mockUser = new User(userId, "Alice", "alice@gmail.com", "pass123", "9876543210", "student");

		when(userService.findUserById(userId)).thenReturn(mockUser);

		ResponseEntity<User> response = userController.getUserById(userId);

		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getUserId()).isEqualTo(userId);
		assertThat(response.getBody().getUserName()).isEqualTo("Alice");
		assertThat(response.getBody().getEmail()).isEqualTo("alice@gmail.com");
	}

	@Test
	void testCreateUser() {
		User inputUser = new User(null, "Bob", "bob@gmail.com", "pass456", "9876500000", "student");
		User savedUser = new User(2L, "Bob", "bob@gmail.com", "pass456", "9876500000", "student");

		when(bindingResult.hasErrors()).thenReturn(false);
		when(userService.createUser(any(User.class))).thenReturn(savedUser);

		ResponseEntity<User> response = userController.createUser(inputUser, bindingResult);

		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getUserId()).isEqualTo(2L);
		assertThat(response.getBody().getUserName()).isEqualTo("Bob");
	}

	@Test
	void testGetAllUsers() {
		User u1 = new User(1L, "Alice", "alice@gmail.com", "pass123", "9876543210", "student");
		User u2 = new User(2L, "Bob", "bob@gmail.com", "pass456", "9876500000", "student");

		List<User> userList = Arrays.asList(u1, u2);

		when(userService.findAllUser()).thenReturn(userList);

		ResponseEntity<List<User>> response = userController.getAllUsers();

		assertThat(response.getBody()).hasSize(2);
		assertThat(response.getBody()).contains(u1, u2);
	}

	@Test
	void testUpdateUser() {
		Long id = 1L;
		User updated = new User(id, "Updated", "updated@gmail.com", "pass", "9999999999", "student");

		when(userService.updateUser(eq(id), any(User.class))).thenReturn(updated);

		ResponseEntity<User> response = userController.updateUser(id, updated, bindingResult);

		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().getUserId()).isEqualTo(id);
		assertThat(response.getBody().getUserName()).isEqualTo("Updated");
	}

	@Test
	void testDeleteUser() {
		Long userId = 1L;

		when(userService.deleteUser(userId)).thenReturn(true);

		ResponseEntity<Void> response = userController.deleteUser(userId);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		assertThat(response.getBody()).isNull();
	}

}
