package com.resumeai.resumescreening.controller;

import com.resumeai.resumescreening.repository.JobRepository;
import com.resumeai.resumescreening.repository.ResumeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Admin and HR dashboard views")
public class HomeController {

    private final JobRepository jobRepository;
    private final ResumeRepository resumeRepository;

    @Operation(summary = "Redirect to login page")
    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login";
    }

    @Operation(summary = "Display dashboard based on user role")
    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_ADMIN")) {
            model.addAttribute("view", "admin");
        } else if (role.equals("ROLE_HR")) {
            model.addAttribute("view", "hr");
            model.addAttribute("jobs", jobRepository.findAll());
        }

        model.addAttribute("totalJobs", jobRepository.count());
        model.addAttribute("totalResumes", resumeRepository.count());

        return "dashboard";
    }
}
