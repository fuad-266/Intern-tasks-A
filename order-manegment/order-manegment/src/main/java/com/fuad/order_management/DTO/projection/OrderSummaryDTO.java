package com.fuad.order_management.DTO.projection;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class OrderSummaryDTO {

    private Long orderId;

    private String customerName;

    private BigDecimal totalAmount;

    private Long itemCount;
}