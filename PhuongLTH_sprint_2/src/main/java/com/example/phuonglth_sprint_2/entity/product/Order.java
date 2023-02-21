package com.example.phuonglth_sprint_2.entity.product;

import com.example.phuonglth_sprint_2.entity.customer.Customer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    private Long codeOrder; // mã đơn hàng
    private boolean orderStatus; // tình trạng đặt hàng
    private boolean paymentStatus; // tình trạng thanh toán
    private String shippingAddress;// địa chỉ giao hàng
    private String orderPhoneNumber;// số điện thoại giao hàng
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    @OneToMany(mappedBy = "order")
    private Set<Customer> customers;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    public Order() {
    }

    public Order(Long idOrder, Long codeOrder, boolean orderStatus, boolean paymentStatus, String shippingAddress, String orderPhoneNumber, Date createDate, Date modifyDate, Set<Customer> customers, Set<OrderDetail> orderDetails) {
        this.idOrder = idOrder;
        this.codeOrder = codeOrder;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.shippingAddress = shippingAddress;
        this.orderPhoneNumber = orderPhoneNumber;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.customers = customers;
        this.orderDetails = orderDetails;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(Long codeOrder) {
        this.codeOrder = codeOrder;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getOrderPhoneNumber() {
        return orderPhoneNumber;
    }

    public void setOrderPhoneNumber(String orderPhoneNumber) {
        this.orderPhoneNumber = orderPhoneNumber;
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

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
