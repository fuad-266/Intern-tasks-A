package com.fuad.order_management.Service.impl;

import com.fuad.order_management.Entity.*;
import com.fuad.order_management.Entity.enums.OrderStatus;
import com.fuad.order_management.Repositary.OrderRepository;
import com.fuad.order_management.Repositary.ProductRepository;
import com.fuad.order_management.Repositary.UserRepository;
import com.fuad.order_management.Service.RelationshipTestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RelationshipTestServiceImpl implements RelationshipTestService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void testRelationships() {

        User user = new User();
        user.setFirstName("Abel");
        user.setLastName("Tesfaye");
        user.setEmail("abel@example.com");

        userRepository.save(user);

        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(BigDecimal.valueOf(1200));
        product.setStock(10);

        productRepository.save(product);

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.CREATED);
        order.setTotalAmount(BigDecimal.valueOf(2400));

        OrderItem item = new OrderItem();
        item.setProduct(product);
        item.setQuantity(2);
        item.setPrice(BigDecimal.valueOf(1200));

        order.addItem(item);

        orderRepository.save(order);

        order.removeItem(item);
    }
}