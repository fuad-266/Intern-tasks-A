package com.example.student_course_system.service;

import com.example.student_course_system.entity.Student;
import com.example.student_course_system.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Pagination
    public Page<Student> getStudents(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    // Filter by age
    public List<Student> getStudentsByAge(int age) {
        return studentRepository.findByAge(age);
    }
}
