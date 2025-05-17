package com.capgemini.courseproject.dto;

import java.time.LocalDate;

public class SubmissionDto {
	
	private Long submissionId;
	private LocalDate submisisonDate;
	private boolean status;
	
	
	public SubmissionDto() {
		super();
	}


	public SubmissionDto(Long submissionId, LocalDate submisisonDate, boolean status) {
		super();
		this.submissionId = submissionId;
		this.submisisonDate = submisisonDate;
		this.status = status;
	}


	public Long getSubmissionId() {
		return submissionId;
	}


	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}


	public LocalDate getSubmisisonDate() {
		return submisisonDate;
	}


	public void setSubmisisonDate(LocalDate submisisonDate) {
		this.submisisonDate = submisisonDate;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
	

}
