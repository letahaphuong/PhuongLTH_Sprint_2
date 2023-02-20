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

    public CategoryProduct() {
    }

    public CategoryProduct(Long idCategory, String nameCategory, Set<Product> products) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.products = products;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
