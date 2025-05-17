package com.capgemini.courseproject.dto;

public class AssignmentDto {
	   private Long assignmentId;
	    private String title;
	    private String description;
	    private Long courseId;
	    private String courseName;

	  
	    public AssignmentDto(Long assignmentId, String title, String description, Long courseId, String courseName) {
	        this.assignmentId = assignmentId;
	        this.title = title;
	        this.description = description;
	        this.courseId = courseId;
	        this.courseName = courseName;
	    }


		public AssignmentDto() {
			super();
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


		public String getDescription() {
			return description;
		}


		public void setDescription(String description) {
			this.description = description;
		}


		public Long getCourseId() {
			return courseId;
		}


		public void setCourseId(Long courseId) {
			this.courseId = courseId;
		}


		public String getCourseName() {
			return courseName;
		}


		public void setCourseName(String courseName) {
			this.courseName = courseName;
		}
	    
	    
}
