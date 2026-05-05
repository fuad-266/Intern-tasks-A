package com.fuad.order_management.Repositary;



import com.fuad.order_management.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query(value = """
        SELECT p.name AS productName,
               COUNT(oi.id) AS totalSold
        FROM order_item oi
        JOIN product p
        ON oi.product_id = p.id
        GROUP BY p.name
        ORDER BY totalSold DESC
        """, nativeQuery = true)
    List<Object[]> getTopSellingProducts();
}