package com.capgemini.courseproject.dto;

import java.time.LocalDate;

public class EnrolledCourseDto {

	private String courseTitle;
	private String instructorName;
	private LocalDate enrollmentDate;
	private Double courseFees;

	public EnrolledCourseDto() {
	}

	public EnrolledCourseDto(String courseTitle, String instructorName, LocalDate enrollmentDate, Double courseFees) {
		super();
		this.courseTitle = courseTitle;
		this.instructorName = instructorName;
		this.enrollmentDate = enrollmentDate;
		this.courseFees = courseFees;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public Double getCourseFees() {
		return courseFees;
	}

	public void setCourseFees(Double courseFees) {
		this.courseFees = courseFees;
	}

}