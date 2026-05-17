package com.security.security_project.service;

import com.security.security_project.auth.AuthRequest;
import com.security.security_project.auth.AuthResponse;
import com.security.security_project.auth.RegisterRequest;
import com.security.security_project.entity.Role;
import com.security.security_project.entity.RoleName;
import com.security.security_project.entity.User;
import com.security.security_project.jwt.JwtUtils;
import com.security.security_project.repository.RoleRepository;
import com.security.security_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.