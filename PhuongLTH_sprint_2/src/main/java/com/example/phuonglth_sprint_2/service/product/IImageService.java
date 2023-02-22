package com.example.phuonglth_sprint_2.service.product;

import com.example.phuonglth_sprint_2.entity.product.Image;
import com.example.phuonglth_sprint_2.entity.product.Product;
import org.springframework.data.repository.query.Param;

public interface IImageService {
    void save(Image image);
    void saveImage(String url, Long idProduct);

}
