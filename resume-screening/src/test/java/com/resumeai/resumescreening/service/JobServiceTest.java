package com.resumeai.resumescreening.service;


import com.resumeai.resumescreening.model.Job;
import com.resumeai.resumescreening.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    private Job job;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        job = new Job(1L, "Java Developer", "Spring Boot and Microservices", null);
    }

    @Test
    void testGetAllJobs() {
        when(jobRepository.findAll()).thenReturn(List.of(job));
        List<Job> result = jobService.getAllJobs();
        assertEquals(1, result.size());
        verify(jobRepository, times(1)).findAll();
    }

    @Test
    void testSaveJob() {
        when(jobRepository.save(job)).thenReturn(job);
        Job saved = jobService.saveJob(job);
        assertEquals("Java Developer", saved.getTitle());
        verify(jobRepository).save(job);
    }

    @Test
    void testGetJobById() {
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));
        Job found = jobService.getJobById(1L);
        assertNotNull(found);
        assertEquals("Java Developer", found.getTitle());
    }

    @Test
    void testDeleteJob() {
        jobService.deleteJob(1L);
        verify(jobRepository).deleteById(1L);
    }
}
