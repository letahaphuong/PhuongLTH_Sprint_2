package com.example.phuonglth_sprint_2.service.product.impl;

import com.example.phuonglth_sprint_2.dto.product.CartTotalPrice;
import com.example.phuonglth_sprint_2.dto.product.CartView;
import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.entity.product.Order;
import com.example.phuonglth_sprint_2.entity.product.OrderDetail;
import com.example.phuonglth_sprint_2.entity.product.Product;
import com.example.phuonglth_sprint_2.repository.product.IOrderDetailRepository;
import com.example.phuonglth_sprint_2.service.product.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<CartView> getCartByIdCustomer(Long idCustomer) {
        return orderDetailRepository.getCartByIdCustomer(idCustomer);
    }

    @Override
    public Boolean existsByProductAndCustomerAndFlagDelete(Product product, Customer customer, boolean flagDelete) {
        return orderDetailRepository.existsByProductAndCustomerAndFlagDelete(product, customer,flagDelete);
    }

    @Override
    public OrderDetail findOrderDetailByProductAndCustomerAndFlagDelete(Product product, Customer customer,boolean flagDelete) {
        return orderDetailRepository.findOrderDetailByProductAndCustomerAndFlagDelete(product,customer,flagDelete);
    }

    @Override
    public void delete(Long id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public OrderDetail findByIdProductOrder(Long id) {
        return orderDetailRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<CartTotalPrice> getCartTotalPrice(Long idCustomer) {
        return orderDetailRepository.getCartTotalPrice(idCustomer);
    }

    @Override
    public CartTotalPrice getCartTotalPriceOb(Long idCustomer) {
        return orderDetailRepository.getCartTotalPriceOb(idCustomer);
    }

    @Override
    public void deleteFlag(Long id) {
        orderDetailRepository.deleteFlag(id);
    }

    @Override
    public List<OrderDetail> getAllForOrder(Long idCustomer) {
        return orderDetailRepository.getAllForOrder(idCustomer);
    }
}
