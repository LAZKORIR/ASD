package com.practice.course.controllers;

import com.practice.course.model.CourseEvent;
import com.practice.course.service.CourseProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseProducer  courseProducer;

    @GetMapping
    public String getCourses() {
        System.out.println("=============Courses endpoint hit=====================");
        return "List of courses";
    }

    @PostMapping("/enroll")
    public String enrollCourse(@RequestBody CourseEvent courseEvent) {
        System.out.println("=============Enroll endpoint hit=====================");
        //("C101", "Spring Boot", "CREATED")
        courseProducer.sendCourseEvent(courseEvent);

        return "Enrolled in course successfully";
    }
}
