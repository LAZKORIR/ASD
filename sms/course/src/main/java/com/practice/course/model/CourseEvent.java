package com.practice.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEvent {
    private String courseId;
    private String courseName;
    private String action; // e.g. "CREATED", "UPDATED"

}
