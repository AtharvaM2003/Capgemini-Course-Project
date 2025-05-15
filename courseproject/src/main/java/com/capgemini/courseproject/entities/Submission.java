package com.capgemini.courseproject.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "submissions")
public class Submission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "submission_id")
    private Long submissionId;

    @Column(name = "submission_date")
    @NotNull(message = "Submission date is required")
    @PastOrPresent(message = "Submission date cannot be in the future")
    private LocalDate submissionDate;

    @Column(name = "status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    @JsonBackReference
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

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
