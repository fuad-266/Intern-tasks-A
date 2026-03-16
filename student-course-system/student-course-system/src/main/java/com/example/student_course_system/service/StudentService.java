package com.example.student_course_system.service;

import com.example.student_course_system.entity.Student;
import com.example.student_course_system.repository.StudentRepository;
import com.example.student_course_system.exception.StudentNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class   StudentService {
    public List<Student> getStudentsByAge(int age){
    return studentRepository.findByAge(age);
}

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Create Student
    public Student createStudent(Student student) {

        if(studentRepository.existsByEmail(student.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        return studentRepository.save(student);
    }

    // Get All Students
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    // Get Student By ID
    public Student getStudentById(Long id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }

    // Update Student
    public Student updateStudent(Long id, Student studentDetails){

        Student student = getStudentById(id);

        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setAge(studentDetails.getAge());

        return studentRepository.save(student);
    }

    // Delete Student
    public void deleteStudent(Long id){

        Student student = getStudentById(id);

        studentRepository.delete(student);
    }
}
