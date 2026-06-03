package com.security.security_project.dto.student;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StudentResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String major;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
