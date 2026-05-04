package com.fuad.order_management.Service;

public interface QueryService {

    void getOrderSummaries();
    void getOrdersByUser(Long userId);
}