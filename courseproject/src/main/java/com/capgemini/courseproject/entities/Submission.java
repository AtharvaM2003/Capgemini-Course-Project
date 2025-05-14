package com.capgemini.courseproject.entities;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
//@Table(name = "submissions")
public class Submission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long submissionId;

//	@ManyToOne
//	@JoinColumn(name = "assignmenId")
	private Assignment assignment;

//	@ManyToOne
//	@JoinColumn(name = "userId")
	private User user;

	private LocalDate submissionDate;

	private boolean status;

	public Submission() {
		super();
	}

	public Submission(Long submissionId, Assignment assignment, User user, LocalDate submissionDate, boolean status) {
		super();
		this.submissionId = submissionId;
		this.assignment = assignment;
		this.user = user;
		this.submissionDate = submissionDate;
		this.status = status;
	}

	public Long getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Submission [submissionId=" + submissionId + ", assignment=" + assignment + ", user=" + user
				+ ", submissionDate=" + submissionDate + ", status=" + status + "]";
	}

}
