package com.resumeai.resumescreening.controller;

import com.resumeai.resumescreening.model.Job;
import com.resumeai.resumescreening.service.JobService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobsController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(JobsControllerTest.MockConfig.class) // ✅ injects mock JobService bean
class JobsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JobService jobService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        JobService jobService() {
            return Mockito.mock(JobService.class);
        }
    }

    @Test
    void testListJobs() throws Exception {
        when(jobService.getAllJobs()).thenReturn(List.of(
                new Job(1L, "Java Dev", "Spring Boot role",null)
        ));

        mockMvc.perform(get("/jobs"))
                .andExpect(status().isOk())
                .andExpect(view().name("jobs"))
                .andExpect(model().attributeExists("jobs"));

        verify(jobService, times(1)).getAllJobs();
    }

    @Test
    void testSaveJob() throws Exception {
        mockMvc.perform(post("/jobs/save")
                        .param("title", "Backend Engineer")
                        .param("description", "Develop APIs"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/jobs"));
    }

    @Test
    void testDeleteJob() throws Exception {
        mockMvc.perform(get("/jobs/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/jobs"));

        verify(jobService).deleteJob(1L);
    }
}
