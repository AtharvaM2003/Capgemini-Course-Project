package com.capgemini.courseproject.dto;

public class CourseInfoDto {
	private String title;
	private String description;

	public CourseInfoDto(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}

	public CourseInfoDto() {
		super();
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

}
