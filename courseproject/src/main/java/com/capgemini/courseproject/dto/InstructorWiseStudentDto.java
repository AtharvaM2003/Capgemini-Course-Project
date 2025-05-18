
package com.capgemini.courseproject.dto;

public class InstructorWiseStudentDto {
    private long studentCount;
    private String instructorName;

    public InstructorWiseStudentDto(long studentCount, String instructorName) {
        this.studentCount = studentCount;
        this.instructorName = instructorName;
    }

    public long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(long studentCount) {
        this.studentCount = studentCount;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }
}
