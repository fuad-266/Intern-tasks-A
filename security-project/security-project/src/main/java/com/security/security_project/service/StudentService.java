package com.security.security_project.service;

import com.security.security_project.dto.student.StudentRequest;
import com.security.security_project.dto.student.StudentResponse;
import com.security.security_project.entity.Student;
import com.security.security_project.entity.User;
import com.security.security_project.exception.ResourceNotFoundException;
import com.security.security_project.mapper.StudentMapper;
import com.security.security_project.repository.StudentRepository;
import com.security.security_project.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    public StudentResponse getStudentById(Long id) {
        return studentMapper.toResponse(findById(id));
    }

    public StudentResponse createStudent(StudentRequest request) {
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }
        User currentUser = SecurityUtils.getCurrentUser();
        Student student = studentMapper.toEntity(request);
        student.setUser(currentUser);
        return studentMapper.toResponse(studentRepository.save(student));
    }

    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student student = findById(id);
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setEmail(request.getEmail());
        student.setPhone(request.getPhone());
        student.setMajor(request.getMajor());
        return studentMapper.toResponse(studentRepository.save(student));
    }

    public void deleteStudent(Long id) {
        studentRepository.delete(findById(id));
    }

    public StudentResponse getMyProfile() {
        User currentUser = SecurityUtils.getCurrentUser();
        Student student = studentRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Profile not found"));
        return studentMapper.toResponse(student);
    }

    private Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }
}
