package com.security.security_project.service;

import com.security.security_project.dto.student.StudentRequest;
import com.security.security_project.dto.student.StudentResponse;
import com.security.security_project.entity.Student;
import com.security.security_project.entity.User;
import com.security.security_project.exception.ResourceNotFoundException;
import com.security.security_project.mapper.StudentMapper;
import com.security.security_project.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import com.security.security_project.utils.SecurityUtils;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock private StudentRepository studentRepository;
    @Mock private StudentMapper studentMapper;

    @InjectMocks private StudentService studentService;

    private MockedStatic<SecurityUtils> securityUtilsMock;
    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = User.builder()
                .username("testuser")
                .email("test@example.com")
                .password("encoded")
                .role("ROLE_STUDENT")
                .build();

        securityUtilsMock = mockStatic(SecurityUtils.class);
        securityUtilsMock.when(SecurityUtils::getCurrentUser).thenReturn(mockUser);
    }

    @AfterEach
    void tearDown() {
        securityUtilsMock.close();
    }

    @Test
    void getAllStudents_shouldReturnList() {
        Student student = new Student();
        StudentResponse response = StudentResponse.builder()
                .id(1L).firstName("John").lastName("Doe").email("john@example.com").build();

        when(studentRepository.findAll()).thenReturn(List.of(student));
        when(studentMapper.toResponse(student)).thenReturn(response);

        List<StudentResponse> result = studentService.getAllStudents();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void getStudentById_shouldThrow_whenNotFound() {
        when(studentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.getStudentById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void createStudent_shouldSaveAndReturn() {
        StudentRequest request = new StudentRequest();
        request.setFirstName("Jane");
        request.setLastName("Smith");
        request.setEmail("jane@example.com");

        Student student = new Student();
        StudentResponse response = StudentResponse.builder()
                .id(1L).firstName("Jane").lastName("Smith").email("jane@example.com").build();

        when(studentRepository.existsByEmail("jane@example.com")).thenReturn(false);
        when(studentMapper.toEntity(request)).thenReturn(student);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toResponse(student)).thenReturn(response);

        StudentResponse result = studentService.createStudent(request);

        assertThat(result.getEmail()).isEqualTo("jane@example.com");
        verify(studentRepository).save(student);
    }

    @Test
    void createStudent_shouldThrow_whenEmailExists() {
        StudentRequest request = new StudentRequest();
        request.setEmail("exists@example.com");

        when(studentRepository.existsByEmail("exists@example.com")).thenReturn(true);

        assertThatThrownBy(() -> studentService.createStudent(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Email already in use");
    }
}
