package com.fuad.order_management.Service.impl;



import com.fuad.order_management.Entity.Order;
import com.fuad.order_management.Repositary.OrderRepository;
import com.fuad.order_management.Service.LazyLoadingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LazyLoadingServiceImpl implements LazyLoadingService {

    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void testNPlusOne() {

        List<Order> orders = orderRepository.findAll();

        System.out.println("========== ORDERS LOADED ==========");

        for (Order order : orders) {

            System.out.println(order.getId());

            System.out.println(order.getUser().getFirstName());
        }
    }
}