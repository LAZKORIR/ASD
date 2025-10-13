package com.resumeai.resumescreening.controller;

import com.resumeai.resumescreening.repository.JobRepository;
import com.resumeai.resumescreening.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final JobRepository jobRepository;
    private final ResumeRepository resumeRepository;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_ADMIN")) {
            model.addAttribute("view", "admin");
            model.addAttribute("totalJobs", jobRepository.count());
            model.addAttribute("totalResumes", resumeRepository.count());
        } else {
            model.addAttribute("view", "hr");
            model.addAttribute("jobs", jobRepository.findAll());
        }
        return "dashboard";
    }
}
