package com.fuad.order_management.Service;

import com.fuad.order_management.DTO.projection.OrderSummaryDTO;
import com.fuad.order_management.DTO.response.OrderResponse;

import java.util.List;

public interface QueryService {

    List<OrderSummaryDTO> getOrderSummaries();

    List<OrderResponse> getOrdersByUser(Long userId);

    Double calculateRevenue();
}