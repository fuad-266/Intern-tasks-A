package com.fuad.order_management.controller;

import com.fuad.order_management.Service.LifecycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final LifecycleService lifecycleService;

    @GetMapping("/test")
    public String testLifecycle() {

        lifecycleService.testEntityStates();

        return "Lifecycle test completed";
    }
}