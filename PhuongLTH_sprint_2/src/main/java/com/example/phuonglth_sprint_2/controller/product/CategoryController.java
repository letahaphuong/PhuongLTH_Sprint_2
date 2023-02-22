package com.example.phuonglth_sprint_2.controller.product;

import com.example.phuonglth_sprint_2.entity.product.CategoryProduct;
import com.example.phuonglth_sprint_2.service.product.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/category")
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    ICategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<List<CategoryProduct>> getAllCategory() {
        List<CategoryProduct> categoryProductList = categoryService.findAll();
        if (categoryProductList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryProductList, HttpStatus.OK);
    }
}
