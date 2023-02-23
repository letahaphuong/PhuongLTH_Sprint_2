package com.example.phuonglth_sprint_2.dto.product;

import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.entity.product.Product;


public class CartDto {
    private Long idProductOrder;

    private int quantityOrder;

    private double price;

    private Product product;

    private Customer customer;

    public CartDto() {
    }

    public CartDto(Long idProductOrder, int quantityOrder, double price, Product product, Customer customer) {
        this.idProductOrder = idProductOrder;
        this.quantityOrder = quantityOrder;
        this.price = price;
        this.product = product;
        this.customer = customer;
    }

    public Long getIdProductOrder() {
        return idProductOrder;
    }

    public void setIdProductOrder(Long idProductOrder) {
        this.idProductOrder = idProductOrder;
    }

    public int getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(int quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
