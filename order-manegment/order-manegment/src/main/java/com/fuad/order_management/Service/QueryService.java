package com.fuad.order_management.Service;

import com.fuad.order_management.DTO.projection.OrderSummaryDTO;

import java.util.List;

public interface QueryService {

    List<OrderSummaryDTO> getOrderSummaries();

    void getOrdersByUser(Long userId);

    Double calculateRevenue();
}