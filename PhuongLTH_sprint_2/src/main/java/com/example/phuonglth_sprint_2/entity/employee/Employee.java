package com.example.phuonglth_sprint_2.entity.employee;

import com.example.phuonglth_sprint_2.entity.account.Account;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;

    private String name;
    private String address;
    private String phone;
    private String email;
    private String idCard;
    @OneToOne
    private Account account;
    @Column(columnDefinition = "date")
    private String dateOfBirth;
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;

    public Employee() {
    }

    public Employee(Long idEmployee, String name, String address, String phone, String email, String idCard, Account account, String dateOfBirth, boolean flagDelete) {
        this.idEmployee = idEmployee;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.idCard = idCard;
        this.account = account;
        this.dateOfBirth = dateOfBirth;
        this.flagDelete = flagDelete;
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
