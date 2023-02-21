package com.example.phuonglth_sprint_2.dto.product;

import javax.validation.constraints.NotBlank;

public class CategoryDto {
    Long idCategory;
    @NotBlank(message = "Tên không được để trống")
    String nameCategory;

    public CategoryDto() {
    }

    public CategoryDto(Long idCategory, String nameCategory) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
