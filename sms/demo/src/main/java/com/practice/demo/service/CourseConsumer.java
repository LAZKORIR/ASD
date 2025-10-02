package com.practice.demo.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.demo.model.CourseEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CourseConsumer {

    @KafkaListener(topics = "course-topic", groupId = "enrollment-group")
    public void consumeCourseEvent(CourseEvent event) {
        System.out.println("📥 Enrollment Service received event: " + event);
        System.out.println("Course Name: " + event.getCourseName());
    }


}
