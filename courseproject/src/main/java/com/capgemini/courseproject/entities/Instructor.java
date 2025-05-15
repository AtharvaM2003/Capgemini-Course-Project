package com.capgemini.courseproject.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "instructors")
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "instructor_id")
    private Long instructorId;

	@NotBlank(message = "Name is mandatory")
	@Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(name = "name")
    private String name;

	@NotBlank(message = "Expertise is mandatory")
    @Size(min = 3, max = 100, message = "Expertise must be between 3 and 100 characters")
    @Column(name = "expertise")
    private String expertise;

    @OneToMany(mappedBy = "instructor")
    @JsonBackReference
    private List<Course> courses;
	public Instructor() {
		super();
	}

	public Instructor(Long instructorId, String name, String expertise, List<Course> courses) {
		super();
		this.instructorId = instructorId;
		this.name = name;
		this.expertise = expertise;
		this.courses = courses;
	}

	public Long getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(Long instructorId) {
		this.instructorId = instructorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Instructor [instructorId=" + instructorId + ", name=" + name + ", expertise=" + expertise + ", courses="
				+ courses + "]";
	}

}