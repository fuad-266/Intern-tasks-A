package com.example.student_course_system.controller;

import com.example.student_course_system.dto.CourseRequestDTO;
import com.example.student_course_system.entity.Course;
import com.example.student_course_system.service.CourseService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Add Course to Student
    @PostMapping("/{studentId}/courses")
    public ResponseEntity<Course> addCourse(
            @PathVariable Long studentId,
            @Valid @RequestBody CourseRequestDTO dto) {

        Course course = new Course();
        course.setName(dto.getName());
        course.setDescription(dto.getDescription());

        Course savedCourse = courseService.addCourseToStudent(studentId, course);

        return ResponseEntity.status(201).body(savedCourse);
    }

    // Get Courses of Student
    @GetMapping("/{studentId}/courses")
    public ResponseEntity<List<Course>> getCourses(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(courseService.getCoursesOfStudent(studentId));
    }
}