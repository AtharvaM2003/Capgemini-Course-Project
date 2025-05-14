package com.capgemini.courseproject.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enrollments")
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long enrollmentId;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "courseId")
	private Course course;

	private LocalDate enrollmentDate;

	public Enrollment() {
		super();
	}

	public Enrollment(Long enrollmentId, User user, Course course, LocalDate enrollmentDate) {
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
