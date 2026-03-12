package com.example.student_course_system.service;

import com.example.student_course_system.entity.Course;
import com.example.student_course_system.entity.Student;
import com.example.student_course_system.repository.CourseRepository;
import com.example.student_course_system.repository.StudentRepository;
import com.example.student_course_system.exception.StudentNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository,
                         StudentRepository studentRepository) {

        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public Course addCourseToStudent(Long studentId, Course course) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found"));

        course.setStudent(student);

        return courseRepository.save(course);
    }

    public List<Course> getCoursesOfStudent(Long studentId) {

        return courseRepository.findByStudentId(studentId);
    }
}