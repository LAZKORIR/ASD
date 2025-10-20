package com.resumeai.resumescreening.repository;

import com.resumeai.resumescreening.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByJobId(Long jobId);

}
