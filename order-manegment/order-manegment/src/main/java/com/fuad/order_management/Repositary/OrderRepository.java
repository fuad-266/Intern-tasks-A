package com.fuad.order_management.Repositary;



import com.fuad.order_management.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}