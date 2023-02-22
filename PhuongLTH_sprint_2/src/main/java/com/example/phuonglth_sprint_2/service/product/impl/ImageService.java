package com.example.phuonglth_sprint_2.service.product.impl;


import com.example.phuonglth_sprint_2.entity.product.Image;
import com.example.phuonglth_sprint_2.repository.product.IImageRepository;
import com.example.phuonglth_sprint_2.service.product.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService implements IImageService {
    @Autowired
    IImageRepository iImageRepository;
    @Override
    public void save(Image image) {
        iImageRepository.save(image);
    }

    @Override
    public void saveImage(String url, Long idProduct) {
        iImageRepository.saveImage(url,idProduct);
    }
}
