package com.fuad.order_management.Service.impl;



import com.fuad.order_management.DTO.projection.OrderSummaryDTO;
import com.fuad.order_management.DTO.projection.TopProductDTO;

import com.fuad.order_management.Repositary.OrderItemRepository;
import com.fuad.order_management.Repositary.OrderRepository;
import com.fuad.order_management.Service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class QueryServiceImpl implements QueryService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<OrderSummaryDTO> getOrderSummaries() {

        List<OrderSummaryDTO> summaries =
                orderRepository.getOrderSummaries();

        for (OrderSummaryDTO dto : summaries) {

            System.out.println(dto.getOrderId());

            System.out.println(dto.getCustomerName());

            System.out.println(dto.getTotalAmount());

            System.out.println(dto.getItemCount());
        }
        return summaries;
    }

    @Override
    public void getOrdersByUser(Long userId) {

        var orders = orderRepository.findOrdersByUserId(userId);

        for (var order : orders) {

            System.out.println(order.getId());

            System.out.println(order.getTotalAmount());

            System.out.println(order.getStatus());
        }
    }
    @Override
    public Double calculateRevenue() {

        return orderRepository.calculateRevenue();
    }
    @Override
    public List<TopProductDTO> getTopSellingProducts() {

        List<Object[]> results =
                orderItemRepository.getTopSellingProducts();

        return results.stream()
                .map(result -> new TopProductDTO(
                        (String) result[0],
                        ((Number) result[1]).longValue()
                ))
                .toList();
    }
}