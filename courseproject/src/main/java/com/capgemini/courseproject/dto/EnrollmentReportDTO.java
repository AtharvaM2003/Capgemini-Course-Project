package com.capgemini.courseproject.dto;

import java.time.LocalDate;

public class EnrollmentReportDTO {
    private Long enrollmentId;
    private String courseTitle;
    private String studentName;
    private LocalDate enrollmentDate;
    

    public EnrollmentReportDTO(Long enrollmentId, String courseTitle, String studentName, LocalDate enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.courseTitle = courseTitle;
        this.studentName = studentName;
        this.enrollmentDate = enrollmentDate;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
