package com.example.phuonglth_sprint_2.service.product.impl;

import com.example.phuonglth_sprint_2.entity.product.CategoryProduct;
import com.example.phuonglth_sprint_2.repository.product.ICategoryRepository;
import com.example.phuonglth_sprint_2.service.product.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public List<CategoryProduct> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryProduct> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(CategoryProduct category) {
        categoryRepository.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void removeFlag(Long id) {

    }
}
