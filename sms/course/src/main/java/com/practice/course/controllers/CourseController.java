package com.practice.course.controllers;

import com.practice.course.model.CourseEvent;
import com.practice.course.service.CourseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseProducer courseProducer;

    // ✅ Example GET endpoint: now trusts gateway
    @GetMapping
    public String getCourses(
            @RequestHeader(value = "X-Gateway-Verified", required = false) String verified,
            @RequestHeader(value = "X-Authenticated-User", required = false) String username) {

        if (!"true".equals(verified)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied: Not from gateway");
        }

        System.out.println("============= Courses endpoint hit =====================");
        System.out.println("Authenticated User: " + username);

        return "Courses for user: " + username;
    }

    // ✅ Example POST endpoint: also trusts gateway and uses username
    @PostMapping("/enroll")
    public String enrollCourse(
            @RequestBody CourseEvent courseEvent,
            @RequestHeader(value = "X-Gateway-Verified", required = false) String verified,
            @RequestHeader(value = "X-Authenticated-User", required = false) String username) {

        if (!"true".equals(verified)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied: Not from gateway");
        }

        System.out.println("============= Enroll endpoint hit =====================");
        System.out.println("Authenticated User: " + username);
        System.out.println("Course Event: " + courseEvent);

        courseProducer.sendCourseEvent(courseEvent);

        return "User " + username + " enrolled in course successfully";
    }
}
