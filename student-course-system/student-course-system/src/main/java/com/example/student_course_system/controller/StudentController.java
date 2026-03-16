package com.example.student_course_system.controller;

import com.example.student_course_system.entity.Student;
import com.example.student_course_system.service.StudentService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<?> getStudents(
            @RequestParam(required = false) Integer age,
            Pageable pageable) {

        // Filtering
        if (age != null) {
            return ResponseEntity.ok(studentService.getStudentsByAge(age));
        }

        // Pagination
        Page<Student> page = studentService.getStudents(pageable);

        Map<String, Object> response = new HashMap<>();

        response.put("content", page.getContent());
        response.put("pageNumber", page.getNumber());
        response.put("pageSize", page.getSize());
        response.put("totalElements", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        response.put("last", page.isLast());

        return ResponseEntity.ok(response);
    }
}
