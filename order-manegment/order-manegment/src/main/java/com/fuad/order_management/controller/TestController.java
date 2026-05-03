package com.fuad.order_management.controller;

import com.fuad.order_management.Service.LifecycleService;
import com.fuad.order_management.Service.RelationshipTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final LifecycleService lifecycleService;
    private final RelationshipTestService relationshipTestService;

    @GetMapping("/test")
    public String testLifecycle() {

        lifecycleService.testEntityStates();

        return "Lifecycle test completed";
    }

    @GetMapping("/relationships")
    public String testRelationships() {

        relationshipTestService.testRelationships();

        return "Relationship test completed";
    }
}