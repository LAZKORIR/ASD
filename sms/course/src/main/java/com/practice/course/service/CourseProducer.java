package com.practice.course.service;

import com.practice.course.model.CourseEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CourseProducer {
    private final KafkaTemplate<String, CourseEvent> kafkaTemplate;

    public CourseProducer(KafkaTemplate<String, CourseEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendCourseEvent(CourseEvent event) {
        kafkaTemplate.send("course-topic", event);
        System.out.println("📤 Sent Course Event: " + event.getCourseName());
    }
}
