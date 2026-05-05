package com.fuad.order_management.Repositary;



import com.fuad.order_management.DTO.projection.OrderSummaryDTO;
import com.fuad.order_management.Entity.Order;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findAll(Pageable pageable);
    @Query("""
            SELECT o
            FROM Order o
            JOIN FETCH o.user
            """)
    List<Order> findAllWithUsers();

    @Query("""
            SELECT new com.fuad.order_management.DTO.projection.OrderSummaryDTO(
                o.id,
                CONCAT(u.firstName, ' ', u.lastName),
                o.totalAmount,
                COUNT(oi.id)
            )
            FROM Order o
            JOIN o.user u
            JOIN o.items oi
            GROUP BY o.id, u.firstName, u.lastName, o.totalAmount
            """)
    List<OrderSummaryDTO> getOrderSummaries();
    @Query("""
        SELECT o
        FROM Order o
        WHERE o.user.id = :userId
        """)
    List<Order> findOrdersByUserId(Long userId);

    @Query("""
        SELECT SUM(o.totalAmount)
        FROM Order o
        """)
    Double calculateRevenue();
}