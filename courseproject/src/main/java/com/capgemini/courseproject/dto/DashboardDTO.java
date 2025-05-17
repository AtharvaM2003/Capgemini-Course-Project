package com.capgemini.courseproject.dto;

public class DashboardDTO {
    private int courseCount;
    private int userCount; // changed from studentCount
    private int instructorCount;
    private int enrollmentCount;

    // getters and setters
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

    public int getEnrollmentCount() {
        return enrollmentCount;
    }

    public int getInstructorCount() {
		return instructorCount;
	}
	public void setInstructorCount(int instructorCount) {
		this.instructorCount = instructorCount;
	}
	public void setEnrollmentCount(int enrollmentCount) {
        this.enrollmentCount = enrollmentCount;
    }
}