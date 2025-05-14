package com.capgemini.courseproject.entities;


import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
    private Long courseId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "fees")
    private double fees;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    @JsonManagedReference 
    private Instructor instructor;

    @OneToMany(mappedBy = "course")
    @JsonBackReference 
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    private List<Assignment> assignments;
	public Course() {
		super();
	}

	
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", title=" + title + ", description=" + description + ", instructor="
				+ instructor + ", enrollments=" + enrollments + ", assignments=" + assignments + ", fees=" + fees + "]";
	}


	public Course(Long courseId, String title, String description, Instructor instructor, List<Enrollment> enrollments,
			List<Assignment> assignments, Double fees) {
		super();
		this.courseId = courseId;
		this.title = title;
		this.description = description;
		this.instructor = instructor;
		this.enrollments = enrollments;
		this.assignments = assignments;
		this.fees = fees;
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

	public Double getFees() {
		return fees;
	}

	public void setFees(Double fees) {
		this.fees = fees;
	}

	
}