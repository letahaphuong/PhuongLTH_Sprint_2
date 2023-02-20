package com.example.phuonglth_sprint_2.controller.product;

import com.example.phuonglth_sprint_2.dto.product.CartDto;
import com.example.phuonglth_sprint_2.service.product.ICategoryService;
import com.example.phuonglth_sprint_2.service.product.IImageService;
import com.example.phuonglth_sprint_2.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
@SessionAttributes("cart")
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IImageService iImageService;

    @ModelAttribute("cart")// khởi tạo giá trị ban đầu cho ss
    public CartDto initCart() {
        return new CartDto();
    }
}
