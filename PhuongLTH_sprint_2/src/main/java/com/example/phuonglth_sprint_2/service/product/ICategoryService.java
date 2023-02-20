package com.example.phuonglth_sprint_2.service.product;

import com.example.phuonglth_sprint_2.entity.product.CategoryProduct;
import com.example.phuonglth_sprint_2.entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<CategoryProduct> findAll();

    Optional<CategoryProduct> findById(Long id);

    void save(CategoryProduct category);

    void remove(Long id);

//    Page<T> searchName(String searchByName, Pageable pageable);

    void removeFlag(Long id);
}
