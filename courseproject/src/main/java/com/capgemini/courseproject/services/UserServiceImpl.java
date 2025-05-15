package com.capgemini.courseproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.courseproject.entities.User;
import com.capgemini.courseproject.exceptions.EmailAlreadyExistsException;
import com.capgemini.courseproject.exceptions.UserAlreadyExistsException;
import com.capgemini.courseproject.exceptions.UserNotFoundException;
import com.capgemini.courseproject.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User createUser(User user) {
	    if (userRepository.existsByEmail(user.getEmail())) {
	        throw new EmailAlreadyExistsException(user.getEmail());
	    }

	    if (userRepository.existsByUserName(user.getUserName())) {
	        throw new UserAlreadyExistsException(user.getUserName());
	    }

	    return userRepository.save(user);
	}

	@Override
	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

	}

	@Override
	public User updateUser(Long id, User updated) {
		User existing = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
		existing.setUserName(updated.getUserName());
		existing.setEmail(updated.getEmail());
		existing.setPassword(updated.getPassword());
		existing.setUserType(updated.getUserType());
		existing.setPhone(updated.getPhone());
		return userRepository.save(existing);
	}

	@Override
	public boolean deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFoundException("User not found with ID: " + id);
		}
		userRepository.deleteById(id);
		return true;
	}

}