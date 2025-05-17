package com.capgemini.courseproject.dto;

public class AssignmentStatusDto {
	
	private Long assignmentId;
	private String title;
	private boolean submitted;
	
	public AssignmentStatusDto () {
		
		
	}
	
	public AssignmentStatusDto(Long assignmentId, String title, boolean submitted) {
		super();
		this.assignmentId = assignmentId;
		this.title = title;
		this.submitted = submitted;
	}

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}
	
	
	
	

}
