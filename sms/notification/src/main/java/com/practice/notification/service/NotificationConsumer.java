package com.practice.notification.service;

import com.practice.notification.model.CourseEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void consumeCourseEvent(CourseEvent event) {
        System.out.println("📥 Notifcation Service received event: " + event);
        System.out.println("Course Name: " + event.getCourseName());

    }
}
