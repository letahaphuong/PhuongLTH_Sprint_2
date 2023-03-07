package com.example.phuonglth_sprint_2.service.product;

import com.example.phuonglth_sprint_2.dto.product.GetInfoSendMail;
import com.example.phuonglth_sprint_2.dto.product.OrderDetailHistory;
import com.example.phuonglth_sprint_2.dto.product.OrderHistory;
import com.example.phuonglth_sprint_2.entity.product.Order;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOrderService {
    String randomCodeOrder();

    void saveOrder(Order order);

    List<GetInfoSendMail> getInfoSendMail(Long idCustomer);

    List<OrderHistory> getHistoryOrder(Long idCustomer);

    Order findById(Long id);

    List<OrderDetailHistory> getOrderDetailHistory(String codeOrder,Long idCustomer);

    Order findOrderByCodeOrder(String codeOrder);

    Long getLastInsertId();

}
