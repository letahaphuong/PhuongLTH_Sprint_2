package com.example.phuonglth_sprint_2.repository.product;

import com.example.phuonglth_sprint_2.dto.customer.GetIdCustomerView;
import com.example.phuonglth_sprint_2.dto.product.GetInfoSendMail;
import com.example.phuonglth_sprint_2.dto.product.OrderDetailHistory;
import com.example.phuonglth_sprint_2.dto.product.OrderHistory;
import com.example.phuonglth_sprint_2.entity.product.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select i.url, p.name_product as nameProduct, p.price, od.quantity_order as quantityOrder\n" +
            "from customer c\n" +
            "         join order_detail od on c.id_customer = od.customer_id_customer\n" +
            "         join product p on p.id_product = od.product_id_product\n" +
            "         join image i on p.id_product = i.product_id_product\n" +
            "where od.flag_delete = false\n" +
            "  and c.id_customer = :id",
            countQuery = "select i.url, p.name_product as nameProduct, p.price, od.quantity_order as quantityOrder\n" +
                    "from customer c\n" +
                    "         join order_detail od on c.id_customer = od.customer_id_customer\n" +
                    "         join product p on p.id_product = od.product_id_product\n" +
                    "         join image i on p.id_product = i.product_id_product\n" +
                    "where od.flag_delete = false\n" +
                    "  and c.id_customer = :id", nativeQuery = true)
    List<GetInfoSendMail> getInfoSendMail(@Param("id") Long idCustomer);

    @Query(value = "select o.code_order     as codeOrder,\n" +
            "       o.payment_status as paymentStatus,\n" +
            "       o.create_date    as createDate,\n" +
            "       c.id_customer    as idCustomer,\n" +
            "       o.address        as orderAddress,\n" +
            "       o.name           as orderName\n" +
            "from orders o\n" +
            "         join customer c on c.id_customer = o.customer_id_customer\n" +
            "         join order_detail od on c.id_customer = od.customer_id_customer\n" +
            "         join product p on p.id_product = od.product_id_product\n" +
            "where o.customer_id_customer = :id\n" +
            "  and od.flag_delete = true\n" +
            "group by o.code_order, o.payment_status,o.name, o.address, o.create_date",
            countQuery = "select o.code_order     as codeOrder,\n" +
                    "       o.payment_status as paymentStatus,\n" +
                    "       o.create_date    as createDate,\n" +
                    "       c.id_customer    as idCustomer,\n" +
                    "       o.address        as orderAddress,\n" +
                    "       o.name           as orderName\n" +
                    "from orders o\n" +
                    "         join customer c on c.id_customer = o.customer_id_customer\n" +
                    "         join order_detail od on c.id_customer = od.customer_id_customer\n" +
                    "         join product p on p.id_product = od.product_id_product\n" +
                    "where o.customer_id_customer = :id\n" +
                    "  and od.flag_delete = true\n" +
                    "group by o.code_order, o.payment_status,o.name, o.address, o.create_date", nativeQuery = true)
    List<OrderHistory> getHistoryOrder(@Param("id") Long idCustomer);

    @Query(value = "select p.name_product    as nameProduct,\n" +
            "       od.quantity_order as quantityOrder,\n" +
            "       od.code_order as codeOrder,\n" +
            "       p.memory as memoryOrder,\n" +
            "       p.price as priceOrder\n" +
            "from customer c\n" +
            "         join order_detail od on c.id_customer = od.customer_id_customer\n" +
            "         join product p on p.id_product = od.product_id_product\n" +
            "where od.code_order = :orderCode\n" +
            "  and od.customer_id_customer = :id\n" +
            "  and od.flag_delete = true", nativeQuery = true)
    List<OrderDetailHistory> getOrderDetailHistory(@Param("orderCode") String codeOrder, @Param("id") Long idCustomer);

    Order findOrderByCodeOrder(String codeOrder);

    @Query(value = "SELECT LAST_INSERT_ID()",nativeQuery = true)
    Long getLastInsertId();
}
