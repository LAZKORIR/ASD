package com.resumeai.resumescreening.controller;

import com.resumeai.resumescreening.model.Job;
import com.resumeai.resumescreening.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobsController {

    private final JobService jobService;

    @GetMapping
    public String listJobs(Model model) {
        System.out.println("Fetching all jobs");
        System.out.println(jobService.getAllJobs());
        model.addAttribute("jobs", jobService.getAllJobs());
        return "jobs";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("job", new Job());
        return "job_form";
    }

    @PostMapping("/save")
    public String saveJob(@ModelAttribute Job job) {
        jobService.saveJob(job);
        return "redirect:/jobs";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return "redirect:/jobs";
    }
}
