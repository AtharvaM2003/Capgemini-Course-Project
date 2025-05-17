package com.capgemini.courseproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "enrollments")
public class Enrollment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "enrollment_id")
	private Long enrollmentId;

	@Column(name = "enrollment_date")
	@PastOrPresent(message = "Future Date not allowed")
	private LocalDate enrollmentDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "user_enroll")
	private User user;

	@ManyToOne
	@JoinColumn(name = "course_id")
	@JsonBackReference(value = "course_enroll")
	private Course course;

	public Enrollment() {
		super();
	}

	public Enrollment(Long enrollmentId, Course course, User user, LocalDate enrollmentDate) {
		super();
		this.enrollmentId = enrollmentId;
		this.user = user;
		this.course = course;
		this.enrollmentDate = enrollmentDate;
	}

	public Long getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(Long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	@Override
	public String toString() {
		return "Enrollment [enrollmentId=" + enrollmentId + ", user=" + user + ", course=" + course
				+ ", enrollmentDate=" + enrollmentDate + "]";
	}

}
