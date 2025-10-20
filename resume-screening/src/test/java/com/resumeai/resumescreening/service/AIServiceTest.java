package com.resumeai.resumescreening.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AIServiceTest {

    private final AIService aiService = new AIService();

    @Test
    void testComputeSimilarity_HighMatch() {
        double score = aiService.computeSimilarity(
                "Java Spring Boot Developer",
                "Experienced Java Spring Boot engineer");
        assertTrue(score > 50);
    }

    @Test
    void testComputeSimilarity_LowMatch() {
        double score = aiService.computeSimilarity(
                "Frontend React Developer",
                "Machine learning specialist");
        assertTrue(score < 50);
    }

    @Test
    void testGenerateSummary_Fallback() {
        // Intentionally triggers failure because no API URL or key set
        String summary = aiService.generateSummary("Sample text");
        assertTrue(summary.contains("failed"));
    }
}
