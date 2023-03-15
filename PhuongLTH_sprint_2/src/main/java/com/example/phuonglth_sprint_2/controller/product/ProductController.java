package com.example.phuonglth_sprint_2.controller.product;

import com.example.phuonglth_sprint_2.dto.product.*;
import com.example.phuonglth_sprint_2.dto.response.ResponseMessage;
import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.entity.product.*;
import com.example.phuonglth_sprint_2.service.customer.ICustomerService;
import com.example.phuonglth_sprint_2.service.product.*;
import com.example.phuonglth_sprint_2.service.send_mail.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product")
@CrossOrigin("*")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IImageService iImageService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IOrderService orderService;

    @Autowired
    ICustomerService customerService;

    @Autowired
    SendMail sendMail;

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

    @GetMapping("/home")
    public ResponseEntity<Page<ProductView>> getAllProductHome(
            @RequestParam(defaultValue = "") String searchs,
            @PageableDefault(size = 8) Pageable pageable
    ) {
        if (searchs != null) {
            Page<ProductView> productViews = productService.getAllProductHome(searchs, pageable);
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

    @PostMapping("create/product")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (productDto.getUrl() == null || productDto.getUrl().trim().isEmpty()) {
            return new ResponseEntity<>(new ResponseMessage("Chưa có hình ảnh sản phẩm"), HttpStatus.BAD_REQUEST);
        } else {
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product);
            productService.save(product);
            Image image = new Image();
            String img = productDto.getUrl();
            image.setUrl(productDto.getUrl());
            Long idProduct = productService.getLastInsertId();
            iImageService.saveImage(img, idProduct);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("cart/create")
    public ResponseEntity<?> createCart(@Valid @RequestBody CartDto cartDto, BindingResult bindingResult) {
        Boolean checkProduct = orderDetailService.existsByProductAndCustomerAndFlagDelete(cartDto.getProduct(), cartDto.getCustomer(), false);
        Product product = productService.findIdProduct(cartDto.getProduct().getIdProduct());
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (product.getQuantity() >= cartDto.getQuantityOrder()){
            if (!checkProduct) {
                OrderDetail orderDetail = new OrderDetail();
                BeanUtils.copyProperties(cartDto, orderDetail);
                orderDetailService.save(orderDetail);
                return new ResponseEntity<>(new ResponseMessage("ok"),HttpStatus.CREATED);

            } else {
                OrderDetail orderDetail1 = orderDetailService.findOrderDetailByProductAndCustomerAndFlagDelete(cartDto.getProduct(), cartDto.getCustomer(), false);
                orderDetail1.setQuantityOrder(orderDetail1.getQuantityOrder() + cartDto.getQuantityOrder());
                orderDetailService.save(orderDetail1);
                return new ResponseEntity<>(new ResponseMessage("ok"),HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(new ResponseMessage("quantity not enough"), HttpStatus.ACCEPTED);
        }

    }

    @DeleteMapping("cart/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") Long id) {
        OrderDetail orderDetail = orderDetailService.findByIdProductOrder(id);
        if (orderDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            orderDetailService.deleteFlag(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long idProduct) {
        Optional<Product> product = productService.finProDuctByIdToDelete(idProduct);
        if (product.isPresent()) {
            productService.removeFlag(idProduct);
            return new ResponseEntity<>(new ResponseMessage("Xoá thành công"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Không tồn tại"), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> editProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult,
                                         @PathVariable("id") Long idProduct) {
        Optional<Product> product = productService.finProDuctByIdToDelete(idProduct);
        if (!product.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Sản phẩm không tồn tại"), HttpStatus.NOT_FOUND);
        } else if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ResponseMessage("Lỗi valid"), HttpStatus.BAD_REQUEST);
        } else {
            Product productCopy = new Product();
            BeanUtils.copyProperties(productDto, productCopy);
            productService.save(productCopy);
            return new ResponseEntity<>(new ResponseMessage("Sửa thành công"), HttpStatus.OK);
        }
    }

    @GetMapping("cart/object/{id}")
    public ResponseEntity<?> getCardByIdCustomer(@PathVariable("id") Long idCustomer) {
        List<CartView> cartView = orderDetailService.getCartByIdCustomer(idCustomer);
        if (cartView == null) {
            return new ResponseEntity<>(new ResponseMessage("Nội dung không được tìm thấy hoặc"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cartView, HttpStatus.OK);
        }
    }

    @GetMapping("cart/total-price/{id}")
    public ResponseEntity<?> getCartTotalPrice(@PathVariable("id") Long idCustomer) {
        Optional<?> getCartTotalPrice = orderDetailService.getCartTotalPrice(idCustomer);
        if (!getCartTotalPrice.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(getCartTotalPrice, HttpStatus.OK);
        }
    }

    @Async
    @PostMapping("order/create")
    public ResponseEntity<?> saveOrder(@Valid @RequestBody OrderDto orderDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new ResponseMessage("Đơn hàng INVALID"), HttpStatus.BAD_REQUEST);
        }
        try {
            if (orderDto == null) {
                return new ResponseEntity<>(new ResponseMessage("Đơn hàng rổng"), HttpStatus.NOT_FOUND);
            } else {
                Customer customer = customerService.findByEmail(orderDto.getEmail()).get();
                Order order = new Order();
                BeanUtils.copyProperties(orderDto, order);
                order.setCodeOrder(orderService.randomCodeOrder());
                order.setCustomer(customer);
                orderService.saveOrder(order);
                Thread.sleep(500);
                List<Product> productList = productService.getAllForOrder(order.getCustomer().getIdCustomer());
                Product[] arr1 = productList.toArray(new Product[0]);
                Thread.sleep(500);
                List<OrderDetail> orderDetailList = orderDetailService.getAllForOrder(order.getCustomer().getIdCustomer());
                OrderDetail[] arr2 = orderDetailList.toArray(new OrderDetail[0]);
                Thread.sleep(1000);
                sendMail.SendEmailToAdmin(orderDto,order);
                Thread.sleep(1500);
                sendMail.SendEmailToCustomerOrder(orderDto,order);
                Long idOrder = orderService.getLastInsertId();
                Thread.sleep(2000);
                for (int i = 0; i < arr1.length; i++) {
                    for (int j = 0; j < arr2.length; j++) {
                        if (arr1[i].getIdProduct().equals(arr2[j].getProduct().getIdProduct())) {
                            Product product = productService.findIdProduct(arr1[i].getIdProduct());
                            OrderDetail orderDetail = orderDetailService.findByIdProductOrder(arr2[j].getIdProductOrder());
                            Order orderSet = orderService.findById(idOrder);
                            orderDetail.setFlagDelete(true);
                            orderDetail.setCodeOrder(orderSet.getCodeOrder());
                            product.setQuantity(product.getQuantity() - arr2[j].getQuantityOrder());
                            productService.save(product);
                            orderDetailService.save(orderDetail);
                        }
                    }
                }
                Thread.sleep(2500);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        } catch (InterruptedException | NullPointerException e) {
            e.getMessage();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/update-quantity")
    public ResponseEntity<?> changeItemQuantityInCart(@RequestParam int quantity, @RequestParam Long idOrderDetail) {
        OrderDetail orderDetail = orderDetailService.findByIdProductOrder(idOrderDetail);
        Product product = productService.findIdProduct(orderDetail.getProduct().getIdProduct());
        if (orderDetail == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else if (quantity > product.getQuantity()){
            return new ResponseEntity<>(new ResponseMessage("quantity not enough"), HttpStatus.ACCEPTED);
        }else {
            orderDetail.setQuantityOrder(quantity);
            orderDetailService.save(orderDetail);
            return new ResponseEntity<>(new ResponseMessage("ok"),HttpStatus.OK);
        }

    }

}
