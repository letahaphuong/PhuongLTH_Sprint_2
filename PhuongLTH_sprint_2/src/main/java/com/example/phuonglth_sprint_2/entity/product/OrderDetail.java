package com.example.phuonglth_sprint_2.entity.product;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductOrder;

    private int quantityOrder;

    private double price;

    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(Long idProductOrder, int quantityOrder, double price, Order order, Product product) {
        this.idProductOrder = idProductOrder;
        this.quantityOrder = quantityOrder;
        this.price = price;
        this.order = order;
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
