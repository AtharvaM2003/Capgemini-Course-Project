package com.capgemini.courseproject.services;

import com.capgemini.courseproject.dto.StudentDto;
import com.capgemini.courseproject.entities.User;
import java.util.List;

public interface UserService {
	User createUser(User user);

	User updateUser(Long id, User updated);

	boolean deleteUser(Long id);

	User findUserById(Long id);

	List<User> findAllUser();

	boolean existsByEmail(String email);

	User findByEmail(String email);

	List<StudentDto> fetchStudentOfCourses(Long id);

	List<StudentDto> fetchAllStudentsNames();

}
