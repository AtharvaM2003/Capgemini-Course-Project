package com.capgemini.courseproject.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Assignment{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long assignmentId;
	
	@NotNull(message = "Course ID is mandatory")
	private Long courseId;
	
	@NotBlank(message = "Title is mandatory")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;
	
	@NotBlank(message = "Description is mandatory")
	private String description;
	
	public Assignment() {
		
	}

	public Assignment(Long assignmentId, Long courseId, String title, String description) {
		super();
		this.assignmentId = assignmentId;
		this.courseId = courseId;
		this.title = title;
		this.description = description;
	}

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
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

	@Override
	public String toString() {
		return "Assignments [AssignmentID=" + assignmentId + ", CourseID=" + courseId + ", Title=" + title
				+ ", Description=" + description + "]";
	}
	
	
}
