package com.security.security_project.controller;

import com.security.security_project.auth.*;
        import com.security.security_project.jwt.JwtService;
import com.security.security_project.repository.UserRepository;
import com.security.security_project.role.Role;
import com.security.security_project.user.User;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.*;
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

        String token =
                jwtService.generateToken(request.getUsername());

        return new AuthResponse(token);
    }
}