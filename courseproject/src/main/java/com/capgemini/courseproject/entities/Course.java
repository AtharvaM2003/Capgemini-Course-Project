package com.capgemini.courseproject.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    @Column(name = "description")
    private String description;

    @Positive(message = "Fees must be positive")
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

    public Course(Long courseId, String title, String description, Instructor instructor,
                  List<Enrollment> enrollments, List<Assignment> assignments, Double fees) {
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

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
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
                + instructor + ", enrollments=" + enrollments + ", assignments=" + assignments + ", fees=" + fees + "]";
    }
}
