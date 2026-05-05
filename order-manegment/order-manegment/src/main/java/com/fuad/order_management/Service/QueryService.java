package com.fuad.order_management.Service;

import com.fuad.order_management.DTO.projection.OrderSummaryDTO;
import com.fuad.order_management.DTO.projection.TopProductDTO;

import java.util.List;

public interface QueryService {

    List<OrderSummaryDTO> getOrderSummaries();
    List<TopProductDTO> getTopSellingProducts();

    void getOrdersByUser(Long userId);

    Double calculateRevenue();
}