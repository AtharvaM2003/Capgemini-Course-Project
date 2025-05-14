package com.capgemini.courseproject.entities;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;

	private String title;

	private String description;

	@ManyToOne
	@JoinColumn(name = "instructorId")
	private Instructor instructor;

	@OneToMany(mappedBy = "course")
	private List<Enrollment> enrollments;

	@OneToMany(mappedBy = "course")
	private List<Assignment> assignments;

	public Course() {
		super();
	}

	public Course(Long courseId, String title, String description, Instructor instructor, List<Enrollment> enrollments,
			List<Assignment> assignments) {
		super();
		this.courseId = courseId;
		this.title = title;
		this.description = description;
		this.instructor = instructor;
		this.enrollments = enrollments;
		this.assignments = assignments;
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

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", title=" + title + ", description=" + description + ", instructor="
				+ instructor + ", enrollments=" + enrollments + ", assignments=" + assignments + "]";
	}

}