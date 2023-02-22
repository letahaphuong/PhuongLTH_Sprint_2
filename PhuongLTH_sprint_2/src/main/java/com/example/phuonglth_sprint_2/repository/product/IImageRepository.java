package com.example.phuonglth_sprint_2.repository.product;


import com.example.phuonglth_sprint_2.entity.product.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    @Modifying
    @Query(value = "insert into image (url, product_id_product) values (:url, :id)",nativeQuery = true)
    @Transactional
    void saveImage(@Param("url") String url, @Param("id") Long idProduct);
}
