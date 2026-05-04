package com.fuad.order_management.Service.impl;

import com.fuad.order_management.Entity.Order;
import com.fuad.order_management.Entity.Product;
import com.fuad.order_management.Entity.User;
import com.fuad.order_management.Entity.enums.OrderStatus;
import com.fuad.order_management.Repositary.OrderRepository;
import com.fuad.order_management.Repositary.ProductRepository;
import com.fuad.order_management.Repositary.UserRepository;
import com.fuad.order_management.Service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void placeOrder() {

        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john@example.com");

        userRepository.save(user);

        Product product = new Product();
        product.setName("Phone");
        product.setPrice(BigDecimal.valueOf(800));
        product.setStock(5);

        productRepository.save(product);

        if (product.getStock() < 1) {
            throw new RuntimeException("Out of stock");
        }

        product.setStock(product.getStock() - 1);

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setTotalAmount(BigDecimal.valueOf(800));

        orderRepository.save(order);

        System.out.println("Order placed successfully");
    }
}