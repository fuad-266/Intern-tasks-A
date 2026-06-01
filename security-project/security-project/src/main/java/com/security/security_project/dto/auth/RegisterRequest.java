package com.security.security_project.dto.auth;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
}