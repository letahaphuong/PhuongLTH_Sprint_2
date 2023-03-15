package com.example.phuonglth_sprint_2.entity.product;

import com.example.phuonglth_sprint_2.entity.customer.Customer;
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

    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;

    private String codeOrder;

    @ManyToOne
    private Product product;

    @OneToOne
    private Customer customer;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public OrderDetail() {
    }

    public OrderDetail(Long idProductOrder, int quantityOrder, double price, boolean flagDelete, String codeOrder, Product product, Customer customer, Date createDate, Date modifyDate) {
        this.idProductOrder = idProductOrder;
        this.quantityOrder = quantityOrder;
        this.price = price;
        this.flagDelete = flagDelete;
        this.codeOrder = codeOrder;
        this.product = product;
        this.customer = customer;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
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

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
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
}
