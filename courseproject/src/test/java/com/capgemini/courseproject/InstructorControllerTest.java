package com.capgemini.courseproject;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import com.capgemini.courseproject.controllers.InstructorController;
import com.capgemini.courseproject.entities.Instructor;
import com.capgemini.courseproject.services.InstructorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class InstructorControllerTest {

    @Mock
    private InstructorService instructorService;

    @InjectMocks
    private InstructorController instructorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    public void testFindInstructorById() {
       
        Long instructorId = 1L;
        Instructor mockInstructor = new Instructor();
        mockInstructor.setInstructorId(instructorId);
        mockInstructor.setName("Ram");

        when(instructorService.findInstructorById(instructorId)).thenReturn(mockInstructor);

        
        ResponseEntity<Instructor> response = instructorController.findInstructorById(instructorId);

        
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getInstructorId()).isEqualTo(instructorId);
        assertThat(response.getBody().getName()).isEqualTo("Ram");
    }
}
