package com.security.security_project.service;

import com.security.security_project.config.JwtConfig;
import com.security.security_project.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JwtServiceTest {

    @Mock
    private JwtConfig jwtConfig;

    @InjectMocks
    private JwtService jwtService;

    private User user;

    @BeforeEach
    void setUp() {
        when(jwtConfig.getSecret())
            .thenReturn("test-secret-key-that-is-long-enough-for-hmac-sha256-algorithm");
        when(jwtConfig.getExpirationMs()).thenReturn(900000L);

        user = User.builder()
                .username("testuser")
                .email("test@example.com")
                .password("encodedpassword")
                .role("ROLE_STUDENT")
                .build();
    }

    @Test
    void generateToken_shouldReturnNonNullToken() {
        String token = jwtService.generateToken(user);
        assertThat(token).isNotNull().isNotEmpty();
    }

    @Test
    void extractUsername_shouldReturnCorrectUsername() {
        String token = jwtService.generateToken(user);
        String username = jwtService.extractUsername(token);
        assertThat(username).isEqualTo("testuser");
    }

    @Test
    void isTokenValid_shouldReturnTrueForValidToken() {
        String token = jwtService.generateToken(user);
        assertThat(jwtService.isTokenValid(token, user)).isTrue();
    }

    @Test
    void isTokenValid_shouldReturnFalseForWrongUser() {
        String token = jwtService.generateToken(user);

        User otherUser = User.builder()
                .username("otheruser")
                .email("other@example.com")
                .password("encodedpassword")
                .role("ROLE_STUDENT")
                .build();

        assertThat(jwtService.isTokenValid(token, otherUser)).isFalse();
    }
}
