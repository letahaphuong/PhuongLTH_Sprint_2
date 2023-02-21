package com.example.phuonglth_sprint_2.service.product.impl;


import com.example.phuonglth_sprint_2.dto.product.ProductView;
import com.example.phuonglth_sprint_2.entity.product.Product;
import com.example.phuonglth_sprint_2.repository.product.IProductRepository;
import com.example.phuonglth_sprint_2.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    IProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductView> getAllProduct(String nameCategory, String nameProduct, String price, Pageable pageable) {
        return productRepository.getAllProduct(nameCategory, nameProduct, price, pageable);
    }

    @Override
    public Optional<ProductView> finProDuctById(String idProduct) {
        return productRepository.finProDuctById(idProduct);
    }

    @Override
    public void removeFlag(Long id) {

    }
}
