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
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "courseId")
	private Long courseId;

	private LocalDate enrollmentDate;

	public Enrollment() {
		super();
	}

	public Enrollment(Long enrollmentId, Long userId, Long courseId, LocalDate enrollmentDate) {
		super();
		this.enrollmentId = enrollmentId;
		this.userId = userId;
		this.courseId = courseId;
		this.enrollmentDate = enrollmentDate;
	}

	public Long getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(Long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	@Override
	public String toString() {
		return "Enrollment [enrollmentId=" + enrollmentId + ", userId=" + userId + ", courseId=" + courseId
				+ ", enrollmentDate=" + enrollmentDate + "]";
	}



}
