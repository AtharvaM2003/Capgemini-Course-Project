package com.capgemini.courseproject.entities;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "assignments")
public class Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "assignment_id")
    private Long assignmentId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    @OneToMany(mappedBy = "assignment")
    @JsonManagedReference
    private List<Submission> submissions;


	public Assignment(Long assignmentId, Course course, String title, String description, List<Submission> submissions) {
	
		this.assignmentId = assignmentId;
		this.course = course;
		this.title = title;
		this.description = description;
		this.submissions = submissions;
	}

	public Assignment() {
		super();
	}

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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

	public List<Submission> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}

	@Override
	public String toString() {
		return "Assignment [assignmentId=" + assignmentId + ", course=" + course + ", title=" + title + ", description="
				+ description + ", submissions=" + submissions + "]";
	}

}
