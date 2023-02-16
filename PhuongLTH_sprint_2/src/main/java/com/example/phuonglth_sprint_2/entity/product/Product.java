package com.example.phuonglth_sprint_2.entity.product;

import com.example.phuonglth_sprint_2.entity.customer.Customer;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String nameProduct;
    @Column(columnDefinition = "text")
    private String description;// miêu tả
    private String imageSensor;// cảm biến hình ảnh
    private String resolution;// độ phân giải
    private String material; // chất liệu
    private String speedRecord; // tốc độ ghi hình
    private String infraredVision; // tầm quan sát
    private String autoWhiteBalanceFunction; // tự động cân bằng sáng

    @OneToMany(mappedBy = "product")
    private Set<Customer> customers;

    @ManyToOne
    private ProductOrder productOrder;

    @ManyToOne
    private CategoryProduct categoryProduct;

    @ManyToOne
    private Image image;
}
