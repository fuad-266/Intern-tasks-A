package com.example.student_course_system.dto;

import jakarta.validation.constraints.NotBlank;

public class CourseRequestDTO {

    @NotBlank(message = "Course name must not be blank")
    private String name;

    private String description;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}