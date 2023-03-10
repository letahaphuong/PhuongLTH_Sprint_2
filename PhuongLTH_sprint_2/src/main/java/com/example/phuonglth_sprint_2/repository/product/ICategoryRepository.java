package com.example.phuonglth_sprint_2.repository.product;

import com.example.phuonglth_sprint_2.entity.product.CategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<CategoryProduct, Long> {
}
