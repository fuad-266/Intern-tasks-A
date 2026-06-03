package com.security.security_project.controller;

import com.security.security_project.BaseIntegrationTest;
import com.security.security_project.dto.auth.AuthResponse;
import com.security.security_project.dto.auth.LoginRequest;
import com.security.security_project.dto.auth.RegisterRequest;
import com.security.security_project.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class AuthControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void register_shouldReturn201_withTokens() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("newuser");
        request.setEmail("newuser@example.com");
        request.setPassword("password123");

        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(
                "/api/auth/register", request, AuthResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getAccessToken()).isNotBlank();
        assertThat(response.getBody().getRefreshToken()).isNotBlank();
    }

    @Test
    void login_shouldReturn200_withTokens() {
        RegisterRequest register = new RegisterRequest();
        register.setUsername("loginuser");
        register.setEmail("loginuser@example.com");
        register.setPassword("password123");
        restTemplate.postForEntity("/api/auth/register", register, AuthResponse.class);

        LoginRequest login = new LoginRequest();
        login.setUsername("loginuser");
        login.setPassword("password123");

        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(
                "/api/auth/login", login, AuthResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getAccessToken()).isNotBlank();
    }

    @Test
    void login_shouldReturn401_withWrongPassword() {
        RegisterRequest register = new RegisterRequest();
        register.setUsername("wrongpassuser");
        register.setEmail("wrongpassuser@example.com");
        register.setPassword("correctpassword");
        restTemplate.postForEntity("/api/auth/register", register, AuthResponse.class);

        LoginRequest login = new LoginRequest();
        login.setUsername("wrongpassuser");
        login.setPassword("wrongpassword");

        ResponseEntity<String> response = restTemplate.postForEntity(
                "/api/auth/login", login, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    void protectedEndpoint_shouldReturn401_withoutToken() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "/api/students", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}
