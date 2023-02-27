package com.example.phuonglth_sprint_2.entity.employee;

import com.example.phuonglth_sprint_2.entity.account.Account;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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
    @Column(columnDefinition = "bit")
    private boolean gender;
    @OneToOne
    private Account account;
    @Column(columnDefinition = "date")
    private String dateOfBirth;
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;
    @Column(columnDefinition = "int default 2")
    private int anony;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    private Date modifyDate;

    public Employee() {
    }

    public Employee(Long idEmployee, String name, String address, String phone, String email, String idCard, boolean gender, Account account, String dateOfBirth, boolean flagDelete, int anony, Date createDate, Date modifyDate) {
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
        this.anony = anony;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
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

    public int getAnony() {
        return anony;
    }

    public void setAnony(int anony) {
        this.anony = anony;
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
