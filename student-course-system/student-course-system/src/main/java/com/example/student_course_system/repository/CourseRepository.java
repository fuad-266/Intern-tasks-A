package com.example.student_course_system.repository;

import com.example.student_course_system.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByStudentId(Long studentId);

}