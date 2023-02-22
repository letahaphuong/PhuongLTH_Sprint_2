package com.example.phuonglth_sprint_2.repository.product;


import com.example.phuonglth_sprint_2.dto.product.ProductView;
import com.example.phuonglth_sprint_2.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p.id_product                  as idProduct,\n" +
            "       p.auto_white_balance_function as autoWhiteBalanceFunction,\n" +
            "       p.create_date                 as createDateProduct,\n" +
            "       p.modify_date                 as modifyDateProduct,\n" +
            "       p.name_product                as nameProduct,\n" +
            "       p.description,\n" +
            "       p.flag_delete                 as flagDelete,\n" +
            "       p.image_sensor                as imageSensor,\n" +
            "       p.infrared_vision             as infraredVision,\n" +
            "       p.material,\n" +
            "       p.memory,\n" +
            "       p.quantity,\n" +
            "       p.resolution,\n" +
            "       p.price,\n" +
            "       p.speed_record                as speedRecord,\n" +
            "       cp.name_category              as nameCategory,\n" +
            "       i.url\n" +
            "from product p\n" +
            "         join category_product cp on cp.id_category = p.category_product_id_category\n" +
            "         join image i on p.id_product = i.product_id_product\n" +
            "where p.flag_delete = false\n" +
            "   and cp.name_category LIKE CONCAT('%', :nameCategory, '%')\n" +
            "   and p.name_product LIKE CONCAT('%', :nameProduct, '%')\n" +
            "   and p.price LIKE CONCAT('%', :price, '%')\n" +
            "order by p.create_date",
            countQuery = "select p.id_product                  as idProduct,\n" +
                    "       p.auto_white_balance_function as autoWhiteBalanceFunction,\n" +
                    "       p.create_date                 as createDateProduct,\n" +
                    "       p.modify_date                 as modifyDateProduct,\n" +
                    "       p.name_product                as nameProduct,\n" +
                    "       p.description,\n" +
                    "       p.flag_delete                 as flagDelete,\n" +
                    "       p.image_sensor                as imageSensor,\n" +
                    "       p.infrared_vision             as infraredVision,\n" +
                    "       p.material,\n" +
                    "       p.memory,\n" +
                    "       p.quantity,\n" +
                    "       p.resolution,\n" +
                    "       p.price,\n" +
                    "       p.speed_record                as speedRecord,\n" +
                    "       cp.name_category              as nameCategory,\n" +
                    "       i.url\n" +
                    "from product p\n" +
                    "         join category_product cp on cp.id_category = p.category_product_id_category\n" +
                    "         join image i on p.id_product = i.product_id_product\n" +
                    "where p.flag_delete = false\n" +
                    "   and cp.name_category LIKE CONCAT('%', :nameCategory, '%')\n" +
                    "   and p.name_product LIKE CONCAT('%', :nameProduct, '%')\n" +
                    "   and p.price LIKE CONCAT('%', :price, '%')\n" +
                    "order by p.create_date", nativeQuery = true)
    Page<ProductView> getAllProduct(@Param("nameCategory") String nameCategory, @Param("nameProduct") String nameProduct, @Param("price") String price, Pageable pageable);

    @Query(value = "select p.id_product                  as idProduct,\n" +
            "       p.auto_white_balance_function as autoWhiteBalanceFunction,\n" +
            "       p.create_date                 as createDateProduct,\n" +
            "       p.modify_date                 as modifyDateProduct,\n" +
            "       p.name_product                as nameProduct,\n" +
            "       p.description,\n" +
            "       p.flag_delete                 as flagDelete,\n" +
            "       p.image_sensor                as imageSensor,\n" +
            "       p.infrared_vision             as infraredVision,\n" +
            "       p.material,\n" +
            "       p.memory,\n" +
            "       p.quantity,\n" +
            "       p.resolution,\n" +
            "       p.price,\n" +
            "       p.speed_record                as speedRecord,\n" +
            "       cp.name_category              as nameCategory,\n" +
            "       i.url\n" +
            "from product p\n" +
            "         join category_product cp on cp.id_category = p.category_product_id_category\n" +
            "         join image i on p.id_product = i.product_id_product\n" +
            "where p.flag_delete = false\n" +
            "   and p.id_product = :idProduct",
            countQuery = "select p.id_product                  as idProduct,\n" +
                    "       p.auto_white_balance_function as autoWhiteBalanceFunction,\n" +
                    "       p.create_date                 as createDateProduct,\n" +
                    "       p.modify_date                 as modifyDateProduct,\n" +
                    "       p.name_product                as nameProduct,\n" +
                    "       p.description,\n" +
                    "       p.flag_delete                 as flagDelete,\n" +
                    "       p.image_sensor                as imageSensor,\n" +
                    "       p.infrared_vision             as infraredVision,\n" +
                    "       p.material,\n" +
                    "       p.memory,\n" +
                    "       p.quantity,\n" +
                    "       p.resolution,\n" +
                    "       p.price,\n" +
                    "       p.speed_record                as speedRecord,\n" +
                    "       cp.name_category              as nameCategory,\n" +
                    "       i.url\n" +
                    "from product p\n" +
                    "         join category_product cp on cp.id_category = p.category_product_id_category\n" +
                    "         join image i on p.id_product = i.product_id_product\n" +
                    "where p.flag_delete = false\n" +
                    "   and p.id_product = :idProduct", nativeQuery = true)
    Optional<ProductView> finProDuctById(@Param("idProduct") String idProduct);

    @Query(value = "SELECT LAST_INSERT_ID()",nativeQuery = true)
    Long getLastInsertId();

    @Transactional
    @Modifying
    @Query(value = "update product\n" +
            "set flag_delete = true where id_product = :idProduct", nativeQuery = true)
    void deleteByFlag(@Param("idProduct") Long idProduct);
}
