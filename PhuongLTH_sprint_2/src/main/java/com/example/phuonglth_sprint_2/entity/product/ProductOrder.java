package com.example.phuonglth_sprint_2.entity.product;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductOrder;

    @OneToMany(mappedBy = "productOrder")
    private Set<Product> products;

    @OneToMany(mappedBy = "productOrder")
    private Set<Order> orders;

    private int quantityOrder;

}
