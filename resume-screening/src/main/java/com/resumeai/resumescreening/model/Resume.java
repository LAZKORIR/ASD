package com.resumeai.resumescreening.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateName;
    private String filePath;

    @Column(length = 10000)
    private String extractedText;

    private Double aiScore;
    private String aiSummary;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;
}
