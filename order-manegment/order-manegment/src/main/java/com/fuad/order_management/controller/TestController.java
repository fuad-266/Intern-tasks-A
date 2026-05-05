package com.fuad.order_management.controller;

import com.fuad.order_management.DTO.projection.TopProductDTO;
import com.fuad.order_management.Entity.enums.OrderStatus;
import com.fuad.order_management.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.fuad.order_management.Entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final LifecycleService lifecycleService;
    private final RelationshipTestService relationshipTestService;
    private final LazyLoadingService lazyLoadingService;
    private final TransactionService transactionService;
    private final QueryService queryService;


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
    @GetMapping("/transaction")
    public String transactionTest() {

        transactionService.placeOrder();

        return "Transaction completed";
    }
    @GetMapping("/summary")
    public String summary() {

        queryService.getOrderSummaries();

        return "Summary generated";
    }
    @GetMapping("/orders/user/{userId}")
    public String ordersByUser(@PathVariable Long userId) {

        queryService.getOrdersByUser(userId);

        return "Orders fetched";
    }
    @GetMapping("/orders/revenue")
    public Double revenue() {

        return queryService.calculateRevenue();
    }
    @GetMapping("/orders/top-products")
    public List<TopProductDTO> topProducts() {

        return queryService.getTopSellingProducts();
    }
    @GetMapping("/orders/pagination")
    public Page<Order> pagination(
            @RequestParam int page,
            @RequestParam int size
    ) {

        return queryService.getOrdersWithPagination(page, size);
    }
    @GetMapping("/orders/status")
    public List<Order> getByStatus(
            @RequestParam OrderStatus status
    ) {

        return queryService.getOrdersByStatus(status);
    }
    @GetMapping("/orders/date-range")
    public List<Order> getOrdersBetweenDates(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end
    ) {

        return queryService.getOrdersBetweenDates(start, end);
    }
}