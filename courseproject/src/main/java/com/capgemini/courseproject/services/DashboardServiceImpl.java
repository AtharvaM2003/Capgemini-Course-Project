package com.capgemini.courseproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.courseproject.dto.DashboardDTO;
import com.capgemini.courseproject.repositories.CourseRepository;
import com.capgemini.courseproject.repositories.EnrollmentRepository;
import com.capgemini.courseproject.repositories.InstructorRepository;
import com.capgemini.courseproject.repositories.UserRepository;

@Service
public class DashboardServiceImpl implements DashboardService {

	private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
	public DashboardServiceImpl(CourseRepository courseRepository, UserRepository userRepository,
			EnrollmentRepository enrollmentRepository, InstructorRepository instructorRepository) {

		this.courseRepository = courseRepository;
		this.userRepository = userRepository;
		this.enrollmentRepository = enrollmentRepository;
		this.instructorRepository = instructorRepository;
	}

    @Override
    public DashboardDTO fetchDashboardCount() {
        DashboardDTO dto = new DashboardDTO();
        dto.setCourseCount((int) courseRepository.count());
        dto.setUserCount( userRepository.findAllStudents().size());
        dto.setInstructorCount((int) instructorRepository.count());
        dto.setEnrollmentCount((int) enrollmentRepository.count());
        return dto;
    }
    }


