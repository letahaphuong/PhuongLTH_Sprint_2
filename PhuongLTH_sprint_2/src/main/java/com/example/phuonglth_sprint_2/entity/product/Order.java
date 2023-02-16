package com.example.phuonglth_sprint_2.entity.product;

import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrder;

    private Long codeOrder; // mã đơn hàng
    private String orderDate; // ngày đặt hàng
    private boolean orderStatus; // tình trạng đặt hàng
    private boolean paymentStatus; // tình trạng thanh toán
    private String shippingAddress;// địa chỉ giao hàng
    private String orderPhoneNumber;// số điện thoại giao hàng

    @ManyToOne
    private ProductOrder productOrder;

}
