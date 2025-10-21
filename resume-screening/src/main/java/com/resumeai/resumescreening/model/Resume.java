package com.resumeai.resumescreening.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Candidate name is required")
    @Size(max = 150, message = "Candidate name must not exceed 150 characters")
    private String candidateName;

    @NotBlank(message = "File path cannot be blank")
    private String filePath;

    @Column(length = 10000)
    private String extractedText;

    private Double aiScore;

    @Size(max = 2000, message = "AI summary cannot exceed 2000 characters")
    private String aiSummary;

    @NotNull(message = "Associated job cannot be null")
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
}
