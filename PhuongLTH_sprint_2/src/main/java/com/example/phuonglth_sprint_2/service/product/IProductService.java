package com.example.phuonglth_sprint_2.service.product;

import com.example.phuonglth_sprint_2.dto.product.ProductView;
import com.example.phuonglth_sprint_2.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

    void remove(Long id);

    Page<ProductView> getAllProduct( String nameCategory, String nameProduct,String price, Pageable pageable);

    Optional<ProductView> finProDuctById(String idProduct);

    void removeFlag(Long id);
}
