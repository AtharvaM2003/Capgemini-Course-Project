package com.capgemini.courseproject.dto;


public class CourseDto {

	private Long courseId;

	private String title;
	
	private String description;

	private Long instructorId;
	
	private String instructorName;
	
	private Double fees;

	
	
	public CourseDto() {
	
	}


	public CourseDto(Long courseId, String title, String description, Long instructorId, String instructorName,
			Double fees) {
		
		this.courseId = courseId;
		this.title = title;
		this.description = description;
		this.instructorId = instructorId;
		this.instructorName = instructorName;
		this.fees = fees;
	}
	

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public Double getFees() {
		return fees;
	}

	public void setFees(Double fees) {
		this.fees = fees;
	}
	
	
	
}
