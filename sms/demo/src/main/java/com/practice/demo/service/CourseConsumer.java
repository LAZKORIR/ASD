package com.practice.demo.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.demo.model.CourseEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CourseConsumer {

    private final KafkaTemplate<String, CourseEvent> kafkaTemplate;

    public CourseConsumer(KafkaTemplate<String, CourseEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "course-topic", groupId = "enrollment-group")
    public void consumeCourseEvent(CourseEvent event) {
        System.out.println("📥 Enrollment Service received event: " + event);
        System.out.println("Course Name: " + event.getCourseName());

        event.setCourseName("[ENROLLED] " + event.getCourseName());
        event.setAction("ENROLLED");

        kafkaTemplate.send("notification-topic", event);
        System.out.println("📤 Sent Course Event to Notification: " + event.getCourseName());
    }


}
