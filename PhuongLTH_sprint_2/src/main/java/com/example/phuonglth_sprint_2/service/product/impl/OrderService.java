package com.example.phuonglth_sprint_2.service.product.impl;

import com.example.phuonglth_sprint_2.dto.product.GetInfoSendMail;
import com.example.phuonglth_sprint_2.dto.product.OrderDetailHistory;
import com.example.phuonglth_sprint_2.dto.product.OrderHistory;
import com.example.phuonglth_sprint_2.dto.response.ResponseMessage;
import com.example.phuonglth_sprint_2.entity.product.Order;
import com.example.phuonglth_sprint_2.repository.product.IOrderRepository;
import com.example.phuonglth_sprint_2.service.product.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IOrderRepository orderRepository;

    @Override
    public String randomCodeOrder() {
        Random generator = new Random();
        Integer codeOrder = generator.nextInt((99999 - 10000) + 1) + 0;
        String codeOrderString = "MDH-" + codeOrder.toString();
        return codeOrderString;

    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public List<GetInfoSendMail> getInfoSendMail(Long idCustomer) {
        return orderRepository.getInfoSendMail(idCustomer);
    }

    @Override
    public List<OrderHistory> getHistoryOrder(Long idCustomer) {
        return orderRepository.getHistoryOrder(idCustomer);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse( null);
    }

    @Override
    public List<OrderDetailHistory> getOrderDetailHistory(String codeOrder, Long idCustomer) {
        return orderRepository.getOrderDetailHistory(codeOrder,idCustomer);
    }

    @Override
    public Order findOrderByCodeOrder(String codeOrder) {
        return orderRepository.findOrderByCodeOrder(codeOrder);
    }

    @Override
    public Long getLastInsertId() {
        return orderRepository.getLastInsertId();
    }
}
