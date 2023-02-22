package com.example.phuonglth_sprint_2.entity.product;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductOrder;

    private int quantityOrder;

    private double price;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;
//    @ManyToOne
//    private Order order;
    @OneToMany(mappedBy = "orderDetail")
    private Set<Order> orders;
    @ManyToOne
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(Long idProductOrder, int quantityOrder, double price, Date createDate, Date modifyDate, Set<Order> orders, Product product) {
        this.idProductOrder = idProductOrder;
        this.quantityOrder = quantityOrder;
        this.price = price;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.orders = orders;
        this.product = product;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
