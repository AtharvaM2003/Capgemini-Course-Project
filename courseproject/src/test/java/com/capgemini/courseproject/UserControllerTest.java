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
    public void testGetAllUsers() {
        List<User> users = Arrays.asList(
            new User(1L, "Alice", "alice@gmail.com", "pass123", "9876543210", "student", null, null),
            new User(2L, "Bob", "bob@gmail.com", "pass456", "9876500000", "student", null, null)
        );

        when(userService.findAllUser()).thenReturn(users);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
    }

    @Test
    public void testFindUserById() {
        Long userId = 1L;
        User mockUser = new User(userId, "Alice", "alice@gmail.com", "pass123", "9876543210", "student", null, null);

        when(userService.findUserById(userId)).thenReturn(mockUser);

        ResponseEntity<User> response = userController.getUser(userId);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUserId()).isEqualTo(userId);
        assertThat(response.getBody().getUserName()).isEqualTo("Alice");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testCreateUser() {
        User inputUser = new User(null, "Bob", "bob@gmail.com", "pass456", "9876500000", "student", null, null);
        User savedUser = new User(2L, "Bob", "bob@gmail.com", "pass456", "9876500000", "student", null, null);

        when(userService.createUser(any(User.class))).thenReturn(savedUser);

        ResponseEntity<User> response = userController.createUser(inputUser);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUserId()).isEqualTo(2L);
        assertThat(response.getBody().getUserName()).isEqualTo("Bob");
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User updatedData = new User(null, "AliceUpdated", "alice_updated@gmail.com", "newpass", "9123456789", "admin", null, null);
        User updatedUser = new User(userId, "AliceUpdated", "alice_updated@gmail.com", "newpass", "9123456789", "admin", null, null);

        when(userService.updateUser(eq(userId), any(User.class))).thenReturn(updatedUser);

        ResponseEntity<User> response = userController.updateUser(userId, updatedData);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getUserName()).isEqualTo("AliceUpdated");
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;

        when(userService.deleteUser(userId)).thenReturn(true); 

        ResponseEntity<User> response = userController.deleteUser(userId);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

}
