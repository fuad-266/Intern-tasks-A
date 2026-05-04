package com.fuad.order_management.DTO.response;



import com.fuad.order_management.Entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {

    private Long id;

    private BigDecimal totalAmount;

    private OrderStatus status;
}