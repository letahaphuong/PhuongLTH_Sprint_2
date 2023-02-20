package com.example.phuonglth_sprint_2.dto.product;

import com.example.phuonglth_sprint_2.entity.product.CategoryProduct;
import com.example.phuonglth_sprint_2.entity.product.Image;

import javax.validation.constraints.NotBlank;

public class ProductDto {
    private Long idProduct;
    @NotBlank(message = "Không được để trống.")
    private String nameProduct;
    @NotBlank(message = "Không được để trống.")
    private String description;// miêu tả
    @NotBlank(message = "Không được để trống.")
    private String imageSensor;// cảm biến hình ảnh
    @NotBlank(message = "Không được để trống.")
    private String resolution;// độ phân giải
    @NotBlank(message = "Không được để trống.")
    private String material; // chất liệu
    @NotBlank(message = "Không được để trống.")
    private String speedRecord; // tốc độ ghi hình
    @NotBlank(message = "Không được để trống.")
    private String infraredVision; // tầm quan sát
    @NotBlank(message = "Không được để trống.")
    private String memory;
    @NotBlank(message = "Không được để trống.")
    private String autoWhiteBalanceFunction; // tự độn
    private CategoryProduct categoryProduct;
    private Image image;

    public ProductDto() {
    }

    public ProductDto(Long idProduct, String nameProduct, String description, String imageSensor, String resolution, String material, String speedRecord, String infraredVision, String memory, String autoWhiteBalanceFunction, CategoryProduct categoryProduct, Image image) {
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
        this.categoryProduct = categoryProduct;
        this.image = image;
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

    public String getAutoWhiteBalanceFunction() {
        return autoWhiteBalanceFunction;
    }

    public void setAutoWhiteBalanceFunction(String autoWhiteBalanceFunction) {
        this.autoWhiteBalanceFunction = autoWhiteBalanceFunction;
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
