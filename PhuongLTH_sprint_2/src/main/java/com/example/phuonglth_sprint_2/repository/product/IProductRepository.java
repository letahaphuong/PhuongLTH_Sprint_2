package com.example.phuonglth_sprint_2.repository.product;


import com.example.phuonglth_sprint_2.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
}
