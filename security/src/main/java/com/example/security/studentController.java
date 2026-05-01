package com.example.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class studentController {


    private List<student> student= new ArrayList<>(List.of(
            new student(  1, "malik", 12),
            new student( 2, "same", 32)
    ));



    @GetMapping("/student")
    public List<student> getStudents(){
        return student;
    }
}
