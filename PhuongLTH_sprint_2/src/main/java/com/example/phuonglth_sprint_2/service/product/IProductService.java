package com.example.phuonglth_sprint_2.service.product;

import com.example.phuonglth_sprint_2.entity.product.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

    void remove(Long id);

//    Page<T> searchName(String searchByName, Pageable pageable);

    void removeFlag(Long id);
}
