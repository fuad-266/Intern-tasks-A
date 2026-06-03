package com.security.security_project.controller;

import com.security.security_project.BaseIntegrationTest;
import com.security.security_project.dto.auth.AuthResponse;
import com.security.security_project.dto.auth.LoginRequest;
import com.security.security_project.dto.student.StudentRequest;
import com.security.security_project.entity.Role;
import com.security.security_project.entity.User;
import com.security.security_project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class StudentControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String studentToken;
    private String adminToken;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        studentToken = createUserAndLogin("student1", "student1@example.com", "password", Role.ROLE_STUDENT);
        adminToken   = createUserAndLogin("admin1", "admin1@example.com", "adminpass", Role.ROLE_ADMIN);
    }

    @Test
    void getAllStudents_shouldReturn403_forStudentRole() {
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/students", HttpMethod.GET,
                bearerEntity(null, studentToken), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    void getAllStudents_shouldReturn200_forAdminRole() {
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/students", HttpMethod.GET,
                bearerEntity(null, adminToken), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void createStudent_shouldReturn201_forStudentRole() {
        StudentRequest request = new StudentRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@example.com");

        ResponseEntity<String> response = restTemplate.exchange(
                "/api/students", HttpMethod.POST,
                bearerEntity(request, studentToken), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void deleteStudent_shouldReturn403_forStudentRole() {
        ResponseEntity<String> response = restTemplate.exchange(
                "/api/students/1", HttpMethod.DELETE,
                bearerEntity(null, studentToken), String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    // --- helpers ---

    private String createUserAndLogin(String username, String email, String password, Role role) {
        userRepository.save(User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(role.name())
                .build());

        LoginRequest login = new LoginRequest();
        login.setUsername(username);
        login.setPassword(password);

        AuthResponse auth = restTemplate.postForObject("/api/auth/login", login, AuthResponse.class);
        return auth.getAccessToken();
    }

    private <T> HttpEntity<T> bearerEntity(T body, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(body, headers);
    }
}
