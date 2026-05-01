package com.fuad.order_management.Repositary;



import com.fuad.order_management.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}