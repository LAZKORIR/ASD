package com.resumeai.resumescreening.service;

import com.resumeai.resumescreening.model.Resume;
import com.resumeai.resumescreening.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {
    private final ResumeRepository resumeRepository;

    public List<Resume> getResumesForJob(Long jobId) {
        return resumeRepository.findByJobId(jobId);
    }

    public Resume save(Resume resume) {
        return resumeRepository.save(resume);
    }
}
