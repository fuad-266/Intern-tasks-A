package com.fuad.order_management.Service.impl;



import com.fuad.order_management.Entity.Product;
import com.fuad.order_management.Service.LifecycleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LifecycleServiceImpl implements LifecycleService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void testEntityStates() {

        System.out.println("========== TRANSIENT ==========");

        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(BigDecimal.valueOf(1200));
        product.setStock(10);

        System.out.println(product.getId());

        System.out.println("========== PERSISTENT ==========");

        entityManager.persist(product);

        System.out.println(product.getId());

        System.out.println("========== DIRTY CHECKING ==========");

        product.setPrice(BigDecimal.valueOf(1500));

        System.out.println("Price changed without save()");

        System.out.println("========== FLUSH ==========");

        entityManager.flush();

        System.out.println("SQL executed");

        System.out.println("========== DETACHED ==========");

        entityManager.detach(product);

        product.setName("Detached Product");

        System.out.println("Detached object modified");

        System.out.println("No UPDATE should happen");
    }
}