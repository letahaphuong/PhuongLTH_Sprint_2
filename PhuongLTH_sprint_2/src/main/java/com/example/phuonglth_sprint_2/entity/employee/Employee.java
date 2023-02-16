package com.example.phuonglth_sprint_2.entity.employee;

import com.example.phuonglth_sprint_2.entity.account.Account;

import javax.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmployee;

    private String nameEmployee;
    private String addressCustomer;
    private String phoneNumber;
    private String email;
    private String idCardEmployee;
    @OneToOne
    private Account account;
    @Column(columnDefinition = "date")
    private String dateOfBirth;
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;
}
