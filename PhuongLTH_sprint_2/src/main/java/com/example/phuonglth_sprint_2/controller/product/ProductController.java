package com.example.phuonglth_sprint_2.controller.product;

import com.example.phuonglth_sprint_2.dto.product.CartDto;
import com.example.phuonglth_sprint_2.dto.product.CategoryDto;
import com.example.phuonglth_sprint_2.dto.product.ProductDto;
import com.example.phuonglth_sprint_2.dto.product.ProductView;
import com.example.phuonglth_sprint_2.dto.response.ResponseMessage;
import com.example.phuonglth_sprint_2.entity.product.CategoryProduct;
import com.example.phuonglth_sprint_2.entity.product.Image;
import com.example.phuonglth_sprint_2.entity.product.Product;
import com.example.phuonglth_sprint_2.service.product.ICategoryService;
import com.example.phuonglth_sprint_2.service.product.IImageService;
import com.example.phuonglth_sprint_2.service.product.IProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IImageService iImageService;

    @PostMapping("category")
    public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto categoryDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            CategoryProduct categoryProduct = new CategoryProduct();
            BeanUtils.copyProperties(categoryDto, categoryProduct);
            categoryService.save(categoryProduct);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<Page<ProductView>> getAllProduct(
            @RequestParam(defaultValue = "") String nameCategory,
            @RequestParam(defaultValue = "") String nameProduct,
            @RequestParam(defaultValue = "") String price,
            @PageableDefault(size = 8) Pageable pageable
    ) {
        if (nameCategory != null && nameProduct != null && price != null) {
            Page<ProductView> productViews = productService.getAllProduct(nameCategory, nameProduct, price, pageable);
            if (productViews != null && productViews.hasContent()) {
                return new ResponseEntity<>(productViews, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductView> findProductById(@PathVariable("id") String idProduct) {
        Optional<ProductView> productView = productService.finProDuctById(idProduct);
        if (!productView.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productView.get(), HttpStatus.OK);
        }

    }

    @PostMapping("")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (productDto.getUrl() == null || productDto.getUrl().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Chưa có hình ảnh sản phẩm"), HttpStatus.BAD_REQUEST);
        } else {
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product);
            Image image = new Image();
            image.setUrl(productDto.getUrl());
            iImageService.save(image);
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
