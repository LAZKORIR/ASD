package com.resumeai.resumescreening.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/login"; // ✅ if not logged in, go to login
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // show dashboard.html
    }
}
