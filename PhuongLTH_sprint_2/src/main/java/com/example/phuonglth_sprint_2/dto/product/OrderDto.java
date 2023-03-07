package com.example.phuonglth_sprint_2.dto.product;

import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.entity.product.Order;
import com.example.phuonglth_sprint_2.entity.product.OrderDetail;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class OrderDto {

    private Long idOrder;

    private String codeOrder; // mã đơn hàng
    private String name; // mã đơn hàng
    private boolean paymentStatus; // tình trạng thanh toán
    private String address;// địa chỉ giao hàng
    private String phone;// số điện thoại giao hàng
    private String email;
    private Customer customer;


    public OrderDto() {
    }

    public OrderDto(Long idOrder, String codeOrder, String name, boolean paymentStatus, String address, String phone, String email, Customer customer) {
        this.idOrder = idOrder;
        this.codeOrder = codeOrder;
        this.name = name;
        this.paymentStatus = paymentStatus;
        this.address = address;
        this.phone = phone;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
