package com.example.phuonglth_sprint_2.entity.customer;

import com.example.phuonglth_sprint_2.entity.account.Account;
import com.example.phuonglth_sprint_2.entity.product.Order;
import com.example.phuonglth_sprint_2.entity.product.OrderDetail;
import com.example.phuonglth_sprint_2.entity.product.Product;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;
    private String name;
    private String email;
    private String address;
    private String idCard;
    @Column(columnDefinition = "bit")
    private boolean gender;
    private String dateOfBirth;
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;
    private String phone;

    @OneToOne
    private Account account;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public Customer() {
    }

    public Customer(Long idCustomer, String name, String email, String address, String idCard, boolean gender, String dateOfBirth, boolean flagDelete, String phone, Account account, Date createDate, Date modifyDate) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.email = email;
        this.address = address;
        this.idCard = idCard;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.flagDelete = flagDelete;
        this.phone = phone;
        this.account = account;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
