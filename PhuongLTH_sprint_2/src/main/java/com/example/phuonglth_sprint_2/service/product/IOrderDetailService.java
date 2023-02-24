package com.example.phuonglth_sprint_2.service.product;

import com.example.phuonglth_sprint_2.dto.product.CartTotalPrice;
import com.example.phuonglth_sprint_2.dto.product.CartView;
import com.example.phuonglth_sprint_2.entity.product.OrderDetail;
import com.example.phuonglth_sprint_2.entity.product.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailService {
    void save(OrderDetail orderDetail);

    List<CartView> getCartByIdCustomer(Long idCustomer);

    Boolean existsByProduct(Product product);

    OrderDetail findOrderDetailByProduct(Product product);

    void delete(Long id);

    OrderDetail findByIdProductOrder(Long id);

    Optional<CartTotalPrice> getCartTotalPrice(Long idCustomer);

}
