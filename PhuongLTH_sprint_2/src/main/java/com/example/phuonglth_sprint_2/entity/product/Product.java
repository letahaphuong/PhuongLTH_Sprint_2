package com.example.phuonglth_sprint_2.entity.product;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String nameProduct;
    @Column(columnDefinition = "text")
    private String description;// miêu tả
    private String imageSensor;// cảm biến hình ảnh
    private String resolution;// độ phân giải
    private String material; // chất liệu
    private String speedRecord; // tốc độ ghi hình
    private String infraredVision; // tầm quan sát
    private String memory;
    private String autoWhiteBalanceFunction; // tự động cân bằng sáng
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    @ManyToOne
    private CategoryProduct categoryProduct;

    @ManyToOne
    private Image image;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public Product() {
    }

    public Product(Long idProduct, String nameProduct, String description, String imageSensor, String resolution, String material, String speedRecord, String infraredVision, String memory, String autoWhiteBalanceFunction, boolean flagDelete, Set<OrderDetail> orderDetails, CategoryProduct categoryProduct, Image image) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.description = description;
        this.imageSensor = imageSensor;
        this.resolution = resolution;
        this.material = material;
        this.speedRecord = speedRecord;
        this.infraredVision = infraredVision;
        this.memory = memory;
        this.autoWhiteBalanceFunction = autoWhiteBalanceFunction;
        this.flagDelete = flagDelete;
        this.orderDetails = orderDetails;
        this.categoryProduct = categoryProduct;
        this.image = image;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageSensor() {
        return imageSensor;
    }

    public void setImageSensor(String imageSensor) {
        this.imageSensor = imageSensor;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSpeedRecord() {
        return speedRecord;
    }

    public void setSpeedRecord(String speedRecord) {
        this.speedRecord = speedRecord;
    }

    public String getInfraredVision() {
        return infraredVision;
    }

    public void setInfraredVision(String infraredVision) {
        this.infraredVision = infraredVision;
    }

    public String getAutoWhiteBalanceFunction() {
        return autoWhiteBalanceFunction;
    }

    public void setAutoWhiteBalanceFunction(String autoWhiteBalanceFunction) {
        this.autoWhiteBalanceFunction = autoWhiteBalanceFunction;
    }

    public Set<OrderDetail> getProductOrders() {
        return orderDetails;
    }

    public void setProductOrders(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public CategoryProduct getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(CategoryProduct categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
