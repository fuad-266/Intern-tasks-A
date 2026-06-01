package com.security.security_project.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}