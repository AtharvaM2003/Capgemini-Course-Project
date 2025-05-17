package com.capgemini.courseproject.dto;

public class StudDashboardDTO {
	    private int courseCount;
	    private int userCount; // changed from studentCount
	    private int instructorCount;
	  
	    public int getCourseCount() {
	        return courseCount;
	    }
	    public void setCourseCount(int courseCount) {
	        this.courseCount = courseCount;
	    }

	    public int getUserCount() {
	        return userCount;
	    }
	    public void setUserCount(int userCount) {
	        this.userCount = userCount;
	    }
	    
	    public int getInstructorCount() {
			return instructorCount;
		}
		public void setInstructorCount(int instructorCount) {
			this.instructorCount = instructorCount;
		}
	}