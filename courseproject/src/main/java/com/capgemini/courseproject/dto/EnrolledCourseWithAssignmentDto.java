package com.capgemini.courseproject.dto;

import java.util.List;

public class EnrolledCourseWithAssignmentDto {

	private Long courseId;
	private String courseTitle;
	private List<AssignmentStatusDto> assignments;

	public EnrolledCourseWithAssignmentDto() {

	}

	public EnrolledCourseWithAssignmentDto(Long courseId, String courseTitle, List<AssignmentStatusDto> assignments) {
		super();
		this.courseId = courseId;
		this.courseTitle = courseTitle;
		this.assignments = assignments;
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

	public List<AssignmentStatusDto> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<AssignmentStatusDto> assignments) {
		this.assignments = assignments;
	}
	
	

	

}
