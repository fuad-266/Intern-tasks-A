package com.security.security_project.auth;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;
}