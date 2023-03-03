package com.example.phuonglth_sprint_2.dto.product;

import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.entity.product.Order;
import com.example.phuonglth_sprint_2.entity.product.OrderDetail;

public class OrderDto {

    private Long idOrder;

    private String codeOrder; // mã đơn hàng
    private boolean paymentStatus; // tình trạng thanh toán
    private String shippingAddress;// địa chỉ giao hàng
    private String orderPhoneNumber;// số điện thoại giao hàng

    private OrderDetail orderDetail;

    public OrderDto() {
    }

    public OrderDto(Long idOrder, String codeOrder, boolean paymentStatus, String shippingAddress, String orderPhoneNumber, OrderDetail orderDetail) {
        this.idOrder = idOrder;
        this.codeOrder = codeOrder;
        this.paymentStatus = paymentStatus;
        this.shippingAddress = shippingAddress;
        this.orderPhoneNumber = orderPhoneNumber;
        this.orderDetail = orderDetail;
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

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }
}
