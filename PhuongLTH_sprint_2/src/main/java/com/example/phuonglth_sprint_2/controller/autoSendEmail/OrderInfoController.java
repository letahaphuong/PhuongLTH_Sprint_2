package com.example.phuonglth_sprint_2.controller.autoSendEmail;

import com.example.phuonglth_sprint_2.dto.product.OrderDetailHistory;
import com.example.phuonglth_sprint_2.dto.product.OrderHistory;
import com.example.phuonglth_sprint_2.entity.product.Order;
import com.example.phuonglth_sprint_2.service.product.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order-info")
@CrossOrigin("*")
public class OrderInfoController {

    @Autowired
    IOrderService orderService;

    @GetMapping("{id}")
    public ResponseEntity<?> getInfoSendMail(@PathVariable("id") Long idCustomer) {
        List<?> list = orderService.getInfoSendMail(idCustomer);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("history/{id}")
    public ResponseEntity<?> getHistoryOrder(@PathVariable("id") Long id) {

        if (id != null) {
            List<OrderHistory> orderHistoryList = orderService.getHistoryOrder(id);
            return new ResponseEntity<>(orderHistoryList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("order-detail-history/{code}")
    public ResponseEntity<?> getOrderDetailHistory(@PathVariable("code") String codeOrder) {
        Order order = orderService.findOrderByCodeOrder(codeOrder);
        if (order != null) {
          List<OrderDetailHistory> orderDetailHistories = orderService.getOrderDetailHistory(codeOrder,order.getCustomer().getIdCustomer());
          return new ResponseEntity<>(orderDetailHistories,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
