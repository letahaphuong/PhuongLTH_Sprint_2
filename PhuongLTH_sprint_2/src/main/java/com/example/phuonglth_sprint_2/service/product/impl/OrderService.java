package com.example.phuonglth_sprint_2.service.product.impl;

import com.example.phuonglth_sprint_2.entity.product.Order;
import com.example.phuonglth_sprint_2.repository.product.IOrderRepository;
import com.example.phuonglth_sprint_2.service.product.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IOrderRepository orderRepository;

    @Override
    public String randomCodeOrder() {
        Random generator = new Random();
        Integer codeOrder = generator.nextInt((999 - 0) + 1) + 0;
        String codeOrderString = "MDH-" + codeOrder.toString();
        return codeOrderString;

    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
