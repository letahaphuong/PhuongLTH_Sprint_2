package com.example.phuonglth_sprint_2.dto.product;

public interface CartView {
    String getNameProduct();
    String getMemory();
    Long getIdProduct();
    Long getIdCustomer();
    Long getIdProductOrder();
    String getNameCategory();
    double getPrice();
    int getQuantity();
}
