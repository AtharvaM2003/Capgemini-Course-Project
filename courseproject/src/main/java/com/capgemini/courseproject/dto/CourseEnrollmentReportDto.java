package com.capgemini.courseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseEnrollmentReportDto {
	private Long courseId;
	private String courseTitle;
	private String instructorName;
	private Long totalEnrollments;
}