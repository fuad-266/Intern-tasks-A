package com.fuad.order_management.Service;

import com.fuad.order_management.DTO.projection.OrderSummaryDTO;
import com.fuad.order_management.DTO.projection.TopProductDTO;
import com.fuad.order_management.Entity.Order;
import com.fuad.order_management.Entity.enums.OrderStatus;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface QueryService {
    List<Order> getOrdersByStatus(OrderStatus status);
    List<OrderSummaryDTO> getOrderSummaries();
    List<TopProductDTO> getTopSellingProducts();
    List<Order> getOrdersBetweenDates(
            LocalDateTime start,
            LocalDateTime end
    );
    void getOrdersByUser(Long userId);

    Double calculateRevenue();
    Page<Order> getOrdersWithPagination(int page, int size);
}