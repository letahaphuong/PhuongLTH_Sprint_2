package com.example.phuonglth_sprint_2.entity.product;

import com.example.phuonglth_sprint_2.entity.customer.Customer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    private String codeOrder; // mã đơn hàng
    private String name; // mã đơn hàng
    private boolean paymentStatus; // tình trạng thanh toán
    private String address;// địa chỉ giao hàng
    private String phone;// số điện thoại giao hàng

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    @ManyToOne
    private Customer customer;


    public Order() {
    }

    public Order(Long idOrder, String codeOrder, String name, boolean paymentStatus, String address, String phone, Customer customer) {
        this.idOrder = idOrder;
        this.codeOrder = codeOrder;
        this.name = name;
        this.paymentStatus = paymentStatus;
        this.address = address;
        this.phone = phone;
        this.customer = customer;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
