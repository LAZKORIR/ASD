package com.resumeai.resumescreening.controller;

import com.resumeai.resumescreening.model.Job;
import com.resumeai.resumescreening.model.Resume;
import com.resumeai.resumescreening.service.AIService;
import com.resumeai.resumescreening.service.JobService;
import com.resumeai.resumescreening.service.ResumeService;
import com.resumeai.resumescreening.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resumes")
public class ResumeController {

    private final ResumeService resumeService;
    private final JobService jobService;
    private final AIService aiService;

    private static final String UPLOAD_DIR = "uploads/";

    @GetMapping("/upload/{jobId}")
    public String showUploadForm(@PathVariable Long jobId, Model model) {
        model.addAttribute("job", jobService.getJobById(jobId));
        return "upload_resume";
    }

    @PostMapping("/upload")
    public String uploadResume(@RequestParam("file") MultipartFile file,
                               @RequestParam("candidateName") String candidateName,
                               @RequestParam("jobId") Long jobId) throws IOException {

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();

        Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
        Files.write(path, file.getBytes());

        Job job = jobService.getJobById(jobId);
        String extractedText = FileUtil.extractText(path.toString());

        double score = aiService.computeSimilarity(job.getDescription(), extractedText);
        String summary = aiService.generateSummary(extractedText);

        Resume resume = Resume.builder()
                .candidateName(candidateName)
                .filePath(path.toString())
                .extractedText(extractedText)
                .aiScore(score)
                .aiSummary(summary)
                .job(job)
                .build();

        resumeService.save(resume);
        return "redirect:/jobs";
    }

    @GetMapping("/list/{jobId}")
    public String listResumes(@PathVariable Long jobId, Model model) {
        model.addAttribute("resumes", resumeService.getResumesForJob(jobId));
        return "resumes_list";
    }
}
