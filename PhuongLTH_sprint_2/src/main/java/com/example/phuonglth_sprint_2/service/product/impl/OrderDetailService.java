package com.example.phuonglth_sprint_2.service.product.impl;

import com.example.phuonglth_sprint_2.dto.product.CartView;
import com.example.phuonglth_sprint_2.entity.product.OrderDetail;
import com.example.phuonglth_sprint_2.repository.product.IOrderDetailRepository;
import com.example.phuonglth_sprint_2.service.product.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailService implements IOrderDetailService {

    @Autowired
    IOrderDetailRepository orderDetailRepository;

    @Override
    public void save(OrderDetail orderDetail) {
    orderDetailRepository.save(orderDetail);
    }

    @Override
    public Optional<CartView> getCartByIdCustomer(Long idCustomer) {
        return orderDetailRepository.getCartByIdCustomer(idCustomer);
    }
}
