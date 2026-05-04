package com.fuad.order_management.controller;

import com.fuad.order_management.Service.LazyLoadingService;
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
    private final LazyLoadingService lazyLoadingService;

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

    @GetMapping("/nplusone")
    public String testNPlusOne() {

        lazyLoadingService.testNPlusOne();

        return "N+1 test completed";
    }
    @GetMapping("/lazy-exception")
    public String lazyException() {

        lazyLoadingService.lazyExceptionDemo();

        return "Lazy exception test";
    }
}