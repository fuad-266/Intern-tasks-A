package com.security.security_project.controller;

import com.security.security_project.auth.AuthResponse;
import com.security.security_project.auth.LoginRequest;
import com.security.security_project.auth.RefreshTokenRequest;
import com.security.security_project.auth.RegisterRequest;

import com.security.security_project.entity.RefreshToken;

import com.security.security_project.jwt.JwtService;

import com.security.security_project.refreshToken.RefreshTokenService;

import com.security.security_project.repository.UserRepository;

import com.security.security_project.role.Role;

import com.security.security_project.user.User;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    // REGISTER
    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request
    ) {

        User user = User.builder()

                .username(request.getUsername())

                .email(request.getEmail())

                .password(
                        passwordEncoder.encode(
                                request.getPassword()
                        )
                )

                .roles(Set.of(Role.ROLE_USER))

                .build();

        userRepository.save(user);

        return "User Registered";
    }

    // LOGIN
    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request
    ) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow();

        String accessToken =
                jwtService.generateToken(user.getUsername());

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        return new AuthResponse(
                accessToken,
                refreshToken.getToken()
        );
    }

    // REFRESH TOKEN
    @PostMapping("/refresh-token")
    public AuthResponse refreshToken(
            @RequestBody RefreshTokenRequest request
    ) {

        RefreshToken refreshToken = refreshTokenService
                .verifyRefreshToken(request.getRefreshToken());

        String accessToken = jwtService.generateToken(
                refreshToken.getUser().getUsername()
        );

        return new AuthResponse(
                accessToken,
                refreshToken.getToken()
        );
    }
}