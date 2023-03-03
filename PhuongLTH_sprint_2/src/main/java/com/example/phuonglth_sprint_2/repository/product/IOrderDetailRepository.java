package com.example.phuonglth_sprint_2.repository.product;

import com.example.phuonglth_sprint_2.dto.product.CartTotalPrice;
import com.example.phuonglth_sprint_2.dto.product.CartView;
import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.entity.product.OrderDetail;
import com.example.phuonglth_sprint_2.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
            "where c.id_customer = :id",
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
                    "where c.id_customer = :id", nativeQuery = true)
    List<CartView> getCartByIdCustomer(@Param("id") Long idCustomer);

    Boolean existsByProductAndCustomer(Product product, Customer customer);

    Boolean existsByCustomer(Customer customer);

    OrderDetail findOrderDetailByProductAndCustomer(Product product, Customer customer);

    @Query(value = "select sum((od.quantity_order * od.price))              as cartTotalPrice\n" +
            "from product p\n" +
            "         join order_detail od on p.id_product = od.product_id_product\n" +
            "         join customer c on c.id_customer = od.customer_id_customer\n" +
            "where c.id_customer = :id",
            countQuery = "select sum((od.quantity_order * od.price))              as cartTotalPrice\n" +
                    "from product p\n" +
                    "         join order_detail od on p.id_product = od.product_id_product\n" +
                    "         join customer c on c.id_customer = od.customer_id_customer\n" +
                    "where c.id_customer = :id", nativeQuery = true)
    Optional<CartTotalPrice> getCartTotalPrice(@Param("id") Long idCustomer);

}
