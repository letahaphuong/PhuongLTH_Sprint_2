package com.example.phuonglth_sprint_2.entity.customer;

import com.example.phuonglth_sprint_2.entity.account.Account;
import com.example.phuonglth_sprint_2.entity.product.Product;

import javax.persistence.*;


@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;
    private String nameCustomer;
    private String emailCustomer;
    private String addressCustomer;
    private String idCardCustomer;
    @Column(columnDefinition = "bit")
    private boolean genderCustomer;
    private String dateOfBirth;
    @Column(columnDefinition = "bit default false")
    private boolean flagDelete;
    private String phoneCustomer1;

    @ManyToOne
    private Product product;
    @OneToOne
    private Account account;

    public Customer() {
    }


}
