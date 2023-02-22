package com.example.phuonglth_sprint_2.repository.product;

import com.example.phuonglth_sprint_2.entity.product.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
