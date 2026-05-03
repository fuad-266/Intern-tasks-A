package com.fuad.order_management.Entity;




import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}