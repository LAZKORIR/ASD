package com.resumeai.resumescreening.controller;

import com.resumeai.resumescreening.model.Job;
import com.resumeai.resumescreening.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jobs")
@Tag(name = "Jobs", description = "Manage job postings")
public class JobsController {

    private final JobService jobService;

    @Operation(summary = "List all job postings")
    @GetMapping
    public String listJobs(Model model) {
        var jobs = jobService.getAllJobs();
        System.out.println("Fetching all jobs: " + jobs);
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    @Operation(summary = "Show job creation form")
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("job", new Job());
        return "job_form";
    }

    @Operation(summary = "Save a new job posting")
    @PostMapping("/save")
    public String saveJob(@Valid @ModelAttribute Job job) {
        jobService.saveJob(job);
        return "redirect:/jobs";
    }

    @Operation(summary = "Delete a job by ID")
    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "redirect:/jobs";
    }
}
