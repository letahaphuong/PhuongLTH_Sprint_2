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
    private double price;
    private int quantity;
    private String autoWhiteBalanceFunction; // tự động cân bằng sáng
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;

//    @ManyToOne
//    private OrderDetail orderDetail;

    @ManyToOne
    private CategoryProduct categoryProduct;


    @OneToMany(mappedBy = "product")
    private Set<Image> images;
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

    public Product(Long idProduct, String nameProduct, String description, String imageSensor, String resolution, String material, String speedRecord, String infraredVision, String memory, double price, int quantity, String autoWhiteBalanceFunction, boolean flagDelete, CategoryProduct categoryProduct, Set<Image> images, Date createDate, Date modifyDate) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.description = description;
        this.imageSensor = imageSensor;
        this.resolution = resolution;
        this.material = material;
        this.speedRecord = speedRecord;
        this.infraredVision = infraredVision;
        this.memory = memory;
        this.price = price;
        this.quantity = quantity;
        this.autoWhiteBalanceFunction = autoWhiteBalanceFunction;
        this.flagDelete = flagDelete;
        this.categoryProduct = categoryProduct;
        this.images = images;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
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

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAutoWhiteBalanceFunction() {
        return autoWhiteBalanceFunction;
    }

    public void setAutoWhiteBalanceFunction(String autoWhiteBalanceFunction) {
        this.autoWhiteBalanceFunction = autoWhiteBalanceFunction;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public CategoryProduct getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(CategoryProduct categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
