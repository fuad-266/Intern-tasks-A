package com.security.security_project.security;

import com.security.security_project.entity.Student;
import com.security.security_project.entity.User;
import com.security.security_project.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("studentSecurity")
@RequiredArgsConstructor
public class SecurityExpression {

    private final StudentRepository studentRepository;

    public boolean isOwner(Long studentId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return false;

        User user = (User) auth.getPrincipal();
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) return false;

        return student.getUser() != null &&
               student.getUser().getId().equals(user.getId());
    }
}
