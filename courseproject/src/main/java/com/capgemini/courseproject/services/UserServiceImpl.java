package com.capgemini.courseproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.courseproject.entities.User;
import com.capgemini.courseproject.exceptions.EmailAlreadyExistsException;
import com.capgemini.courseproject.exceptions.UserAlreadyExistsException;
import com.capgemini.courseproject.exceptions.UserNotFoundException;
import com.capgemini.courseproject.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User createUser(User user) {
		log.debug("Attempting to create a new user with email: {}", user.getEmail());

		if (userRepository.existsByEmail(user.getEmail())) {
			log.warn("Email already exists: {}", user.getEmail());
			throw new EmailAlreadyExistsException(user.getEmail());
		}

		if (userRepository.existsByUserName(user.getUserName())) {
			log.warn("Username already exists: {}", user.getUserName());
			throw new UserAlreadyExistsException(user.getUserName());
		}

		User savedUser = userRepository.save(user);
		log.info("User created successfully with ID: {}", savedUser.getUserId());
		return savedUser;
	}

	@Override
	public List<User> findAllUser() {
		log.debug("Fetching all users");
		return userRepository.findAll();
	}

	@Override
	public User findUserById(Long id) {
		log.debug("Fetching user by ID: {}", id);
		return userRepository.findById(id).orElseThrow(() -> {
			log.error("User not found with ID: {}", id);
			return new UserNotFoundException("User not found with ID: " + id);
		});
	}

	@Override
	public User updateUser(Long id, User updated) {
		log.debug("Attempting to update user with ID: {}", id);

		User existing = userRepository.findById(id).orElseThrow(() -> {
			log.error("User not found for update with ID: {}", id);
			return new UserNotFoundException("User not found with ID: " + id);
		});

		existing.setUserName(updated.getUserName());
		existing.setEmail(updated.getEmail());
		existing.setPassword(updated.getPassword());
		existing.setUserType(updated.getUserType());
		existing.setPhone(updated.getPhone());

		User savedUser = userRepository.save(existing);
		log.info("User updated successfully with ID: {}", savedUser.getUserId());
		return savedUser;
	}

	@Override
	public boolean deleteUser(Long id) {
		log.debug("Attempting to delete user with ID: {}", id);

		if (!userRepository.existsById(id)) {
			log.error("Cannot delete; user not found with ID: {}", id);
			throw new UserNotFoundException("User not found with ID: " + id);
		}

		userRepository.deleteById(id);
		log.info("User deleted successfully with ID: {}", id);
		return true;
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found with this email"));
	}
	
	@Override
    public List<User> findAllStudents() {
        return userRepository.findAllStudents();
    }
}
