package com.example.phuonglth_sprint_2.service.product;

import com.example.phuonglth_sprint_2.dto.product.CartView;
import com.example.phuonglth_sprint_2.entity.product.OrderDetail;

import java.util.Optional;

public interface IOrderDetailService {
    void save(OrderDetail orderDetail);

    Optional<CartView> getCartByIdCustomer(Long idCustomer);

}
