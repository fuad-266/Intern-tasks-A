package com.fuad.order_management.Service.impl;



import com.fuad.order_management.DTO.projection.OrderSummaryDTO;
import com.fuad.order_management.Repositary.OrderRepository;
import com.fuad.order_management.Service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryServiceImpl implements QueryService {

    private final OrderRepository orderRepository;

    @Override
    public void getOrderSummaries() {

        List<OrderSummaryDTO> summaries =
                orderRepository.getOrderSummaries();

        for (OrderSummaryDTO dto : summaries) {

            System.out.println(dto.getOrderId());

            System.out.println(dto.getCustomerName());

            System.out.println(dto.getTotalAmount());

            System.out.println(dto.getItemCount());
        }
    }
}