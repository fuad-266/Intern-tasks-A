package com.fuad.order_management.Repositary;


import com.fuad.order_management.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}