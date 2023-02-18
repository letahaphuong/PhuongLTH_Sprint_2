package com.example.phuonglth_sprint_2.dto.customer;

import com.example.phuonglth_sprint_2.entity.account.Account;
import com.example.phuonglth_sprint_2.entity.product.Product;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

public class CustomerDto {

    private Long idCustomer;
    @NotBlank(message = "Không được để trống.")
    private String name;
    @NotBlank(message = "Không được để trống.")
    private String email;
    @NotBlank(message = "Không được để trống.")
    private String address;
    @NotBlank(message = "Không được để trống.")
    private String idCard;
    @Column(columnDefinition = "bit")
    private boolean gender;
    private String dateOfBirth;
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;
    @NotBlank(message = "Không được để trống.")
    private String phone;
    private Product product;
    private Account account;
    private String encryptPassword;

    public CustomerDto() {
    }

    public CustomerDto(Long idCustomer, String name, String email, String address, String idCard, boolean gender, String dateOfBirth, boolean flagDelete, String phone, Product product, Account account, String encryptPassword) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.email = email;
        this.address = address;
        this.idCard = idCard;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.flagDelete = flagDelete;
        this.phone = phone;
        this.product = product;
        this.account = account;
        this.encryptPassword = encryptPassword;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }
}
