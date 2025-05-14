package com.capgemini.courseproject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.courseproject.entities.User;
import com.capgemini.courseproject.repositories.UserRepository;
import com.capgemini.courseproject.services.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Should save user and return the saved instance")
    void shouldSaveUser() {
        User user = new User(1L, "aarya", "aarya@gmail.com", "aarya123", "9876543210", "student", null, null);
        
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.createUser(user);

        assertNotNull(savedUser, "Saved user should not be null");
        assertEquals("aarya", savedUser.getUserName());
        assertEquals("aarya@gmail.com", savedUser.getEmail());
    }

    @Test
    @DisplayName("Should find user by ID successfully")
    void shouldFindUserById() {
        User user = new User(1L, "aarya", "aarya@gmail.com", "aarya123", "9876543210", "student", null, null);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.findUserById(1L);

        assertNotNull(foundUser);
        assertEquals("aarya", foundUser.getUserName());
        assertEquals("student", foundUser.getUserType());
    }
}