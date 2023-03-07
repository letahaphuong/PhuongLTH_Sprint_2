package com.example.phuonglth_sprint_2.repository.product;

import com.example.phuonglth_sprint_2.dto.product.CartTotalPrice;
import com.example.phuonglth_sprint_2.dto.product.CartView;
import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.entity.product.Order;
import com.example.phuonglth_sprint_2.entity.product.OrderDetail;
import com.example.phuonglth_sprint_2.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query(value = "select p.name_product                 as nameProduct,\n" +
            "       p.memory,\n" +
            "       p.id_product as idProduct,\n" +
            "       c.id_customer as idCustomer,\n" +
            "       od.id_product_order as idProductOrder,\n" +
            "       cp.name_category as nameCategory,\n" +
            "       p.price,\n" +
            "       od.quantity_order as quantity,\n" +
            "       i.url\n" +
            "from product p\n" +
            "         join order_detail od on p.id_product = od.product_id_product\n" +
            "         join customer c on c.id_customer = od.customer_id_customer\n" +
            "         join category_product cp on cp.id_category = p.category_product_id_category\n" +
            "         join image i on p.id_product = i.product_id_product\n" +
            "where od.flag_delete = false and c.id_customer = :id",
            countQuery = "select p.name_product                 as nameProduct,\n" +
                    "       p.memory,\n" +
                    "       p.id_product as idProduct,\n" +
                    "       c.id_customer as idCustomer,\n" +
                    "       od.id_product_order as idProductOrder,\n" +
                    "       cp.name_category as nameCategory,\n" +
                    "       p.price,\n" +
                    "       od.quantity_order as quantity,\n" +
                    "       i.url\n" +
                    "from product p\n" +
                    "         join order_detail od on p.id_product = od.product_id_product\n" +
                    "         join customer c on c.id_customer = od.customer_id_customer\n" +
                    "         join category_product cp on cp.id_category = p.category_product_id_category\n" +
                    "         join image i on p.id_product = i.product_id_product\n" +
                    "where od.flag_delete = false and c.id_customer = :id", nativeQuery = true)
    List<CartView> getCartByIdCustomer(@Param("id") Long idCustomer);

    Boolean existsByProductAndCustomerAndFlagDelete(Product product, Customer customer, boolean flagDelete);

    Boolean existsByCustomer(Customer customer);

    OrderDetail findOrderDetailByProductAndCustomerAndFlagDelete(Product product, Customer customer,boolean flagDelete);

    @Query(value = "select sum((od.quantity_order * od.price))              as cartTotalPrice\n" +
            "from product p\n" +
            "         join order_detail od on p.id_product = od.product_id_product\n" +
            "         join customer c on c.id_customer = od.customer_id_customer\n" +
            "where od.flag_delete = false and c.id_customer = :id",
            countQuery = "select sum((od.quantity_order * od.price))              as cartTotalPrice\n" +
                    "from product p\n" +
                    "         join order_detail od on p.id_product = od.product_id_product\n" +
                    "         join customer c on c.id_customer = od.customer_id_customer\n" +
                    "where od.flag_delete = false and c.id_customer = :id ", nativeQuery = true)
    Optional<CartTotalPrice> getCartTotalPrice(@Param("id") Long idCustomer);

    @Transactional
    @Modifying
    @Query(value = "update order_detail set flag_delete = true where id_product_order = :id", nativeQuery = true)
    void deleteFlag(@Param("id") Long id);

    @Query(value = "select od.*\n" +
            "from customer c\n" +
            "         join order_detail od on c.id_customer = od.customer_id_customer\n" +
            "         join product p on p.id_product = od.product_id_product\n" +
            "where od.flag_delete = false and id_customer = :id", nativeQuery = true)
    List<OrderDetail> getAllForOrder(@Param(("id")) Long idCustomer);

    @Query(value = "select sum((od.quantity_order * od.price))              as cartTotalPrice\n" +
            "from product p\n" +
            "         join order_detail od on p.id_product = od.product_id_product\n" +
            "         join customer c on c.id_customer = od.customer_id_customer\n" +
            "where od.flag_delete = false and c.id_customer = :id",
            countQuery = "select sum((od.quantity_order * od.price))              as cartTotalPrice\n" +
                    "from product p\n" +
                    "         join order_detail od on p.id_product = od.product_id_product\n" +
                    "         join customer c on c.id_customer = od.customer_id_customer\n" +
                    "where od.flag_delete = false and c.id_customer = :id ", nativeQuery = true)
    CartTotalPrice getCartTotalPriceOb(@Param("id") Long idCustomer);

}
