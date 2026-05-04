package com.fuad.order_management.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    private String name;

    private BigDecimal price;

    private Integer stock;

    @Version
    private Long version;
}