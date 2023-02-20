package com.example.phuonglth_sprint_2.service.product.impl;


import com.example.phuonglth_sprint_2.entity.product.Product;
import com.example.phuonglth_sprint_2.repository.product.IProductRepository;
import com.example.phuonglth_sprint_2.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void removeFlag(Long id) {

    }
}
