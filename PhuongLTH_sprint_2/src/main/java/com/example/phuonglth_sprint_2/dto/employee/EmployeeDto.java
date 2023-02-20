package com.example.phuonglth_sprint_2.dto.employee;

import com.example.phuonglth_sprint_2.entity.account.Account;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

public class EmployeeDto {
    private Long idEmployee;
    @NotBlank(message = "Không được để trống")
    private String name;
    @NotBlank(message = "Không được để trống")
    private String address;
    @NotBlank(message = "Không được để trống")
    private String phone;
    @NotBlank(message = "Không được để trống")
    private String email;
    @NotBlank(message = "Không được để trống")
    private String idCard;
    @Column(columnDefinition = "bit")
    private boolean gender;
    private Account account;
    @NotBlank(message = "Không được để trống")
    private String dateOfBirth;
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;
    private String encryptPassword;
    private String avatar;

    public EmployeeDto() {
    }

    public EmployeeDto(Long idEmployee, String name, String address, String phone, String email, String idCard, boolean gender, Account account, String dateOfBirth, boolean flagDelete, String encryptPassword, String avatar) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.idCard = idCard;
        this.gender = gender;
        this.account = account;
        this.dateOfBirth = dateOfBirth;
        this.flagDelete = flagDelete;
        this.encryptPassword = encryptPassword;
        this.avatar = avatar;
    }

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
}
