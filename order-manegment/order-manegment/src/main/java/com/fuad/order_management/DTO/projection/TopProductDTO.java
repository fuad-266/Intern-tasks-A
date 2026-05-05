package com.fuad.order_management.DTO.projection;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

    @Getter
    @Setter
    @AllArgsConstructor
    public class TopProductDTO {

        private String productName;

        private Long totalSold;
    }