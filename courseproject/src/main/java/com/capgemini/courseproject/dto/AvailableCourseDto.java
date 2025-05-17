package com.capgemini.courseproject.dto;

public class AvailableCourseDto {
	private Long courId;

	private String courTitle;

	private String courDescription;

	private Long instructid;

	private String instructName;

	private Double courFees;

	private boolean isEnrolled;

	public AvailableCourseDto() {
	}

	public AvailableCourseDto(Long courId, String courTitle, String courDescription, Long instructid,
			String instructName, Double courFees, boolean isEnrolled) {
		super();
		this.courId = courId;
		this.courTitle = courTitle;
		this.courDescription = courDescription;
		this.instructid = instructid;
		this.instructName = instructName;
		this.courFees = courFees;
		this.isEnrolled = isEnrolled;
	}

	public Long getCourId() {
		return courId;
	}

	public void setCourId(Long courId) {
		this.courId = courId;
	}

	public String getCourTitle() {
		return courTitle;
	}

	public void setCourTitle(String courTitle) {
		this.courTitle = courTitle;
	}

	public String getCourDescription() {
		return courDescription;
	}

	public void setCourDescription(String courDescription) {
		this.courDescription = courDescription;
	}

	public Long getInstructid() {
		return instructid;
	}

	public void setInstructid(Long instructid) {
		this.instructid = instructid;
	}

	public String getInstructName() {
		return instructName;
	}

	public void setInstructName(String instructName) {
		this.instructName = instructName;
	}

	public Double getCourFees() {
		return courFees;
	}

	public void setCourFees(Double courFees) {
		this.courFees = courFees;
	}

	public boolean isEnrolled() {
		return isEnrolled;
	}

	public void setEnrolled(boolean isEnrolled) {
		this.isEnrolled = isEnrolled;
	}

}
