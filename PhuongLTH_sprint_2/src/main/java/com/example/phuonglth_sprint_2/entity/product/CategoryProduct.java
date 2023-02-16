package com.example.phuonglth_sprint_2.entity.product;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CategoryProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    private String nameCategory;

    @OneToMany(mappedBy = "categoryProduct")
    private Set<Product> products;

}
