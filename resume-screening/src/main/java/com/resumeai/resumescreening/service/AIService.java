package com.resumeai.resumescreening.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AIService {

    @Value("${openai.api.key}")
    private String openAiKey;

    @Value("${openai.api.url}")
    private String openAiUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generateSummary(String resumeText) {
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("model", "gpt-3.5-turbo");
            payload.put("messages", new Object[]{
                    Map.of("role", "system", "content", "You are a helpful assistant that summarizes resumes."),
                    Map.of("role", "user", "content", "Summarize the following resume text in 3-4 lines:\n" + resumeText)
            });

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(openAiKey);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(openAiUrl, request, String.class);

            JsonNode json = objectMapper.readTree(response.getBody());
            return json.path("choices").get(0).path("message").path("content").asText();

        } catch (Exception e) {
            e.printStackTrace();
            return "AI Summary generation failed.";
        }
    }

    public double computeSimilarity(String jobDesc, String resumeText) {
        // Simple placeholder using overlap (you can upgrade this to embeddings)
        String[] jdWords = jobDesc.toLowerCase().split("\\W+");
        String[] resumeWords = resumeText.toLowerCase().split("\\W+");

        int matches = 0;
        for (String w1 : jdWords) {
            for (String w2 : resumeWords) {
                if (w1.equals(w2)) {
                    matches++;
                    break;
                }
            }
        }
        double ratio = (double) matches / jdWords.length;
        return Math.min(ratio * 100, 100.0); // percentage match
    }
}
