package com.example.phuonglth_sprint_2.entity.product;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;

    private String url;

    @OneToMany(mappedBy = "image")
    private Set<Product> productsImage;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public Image() {
    }

    public Image(Long idImage, String url, Set<Product> productsImage) {
        this.idImage = idImage;
        this.url = url;
        this.productsImage = productsImage;
    }

    public Long getIdImage() {
        return idImage;
    }

    public void setIdImage(Long idImage) {
        this.idImage = idImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Product> getProductsImage() {
        return productsImage;
    }

    public void setProductsImage(Set<Product> productsImage) {
        this.productsImage = productsImage;
    }
}
