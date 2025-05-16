package com.capgemini.courseproject.dto;

public class CourseEnrollmentDto {
	private Long courseId;
	private String courseTitle;
	private String instructorName;
	private Long totalEnrollments;

	public CourseEnrollmentDto(Long courseId, String courseTitle, String instructorName, Long totalEnrollments) {
		super();
		this.courseId = courseId;
		this.courseTitle = courseTitle;
		this.instructorName = instructorName;
		this.totalEnrollments = totalEnrollments;
	}

	public CourseEnrollmentDto() {
		super();
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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

	public Long getTotalEnrollments() {
		return totalEnrollments;
	}

	public void setTotalEnrollments(Long totalEnrollments) {
		this.totalEnrollments = totalEnrollments;
	}

}
