package com.example.phuonglth_sprint_2.entity.product;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    private String url;

    @OneToMany(mappedBy = "image")
    private Set<Product> productsImage;
}
