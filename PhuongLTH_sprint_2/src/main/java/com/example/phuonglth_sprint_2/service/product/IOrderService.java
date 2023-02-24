package com.example.phuonglth_sprint_2.service.product;

import com.example.phuonglth_sprint_2.entity.product.Order;

public interface IOrderService {
    String randomCodeOrder();

    void saveOrder(Order order);
}
