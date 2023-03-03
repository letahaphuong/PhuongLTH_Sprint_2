package com.example.phuonglth_sprint_2.service.customer;

import com.example.phuonglth_sprint_2.dto.customer.CustomerView;
import com.example.phuonglth_sprint_2.dto.customer.GetIdCustomerView;
import com.example.phuonglth_sprint_2.entity.account.Account;
import com.example.phuonglth_sprint_2.entity.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Customer save(Customer customer);

    Page<CustomerView> getAllCustomer(String name, String email, Pageable pageable);

    void removeFlag(Long id);

    Optional<Customer> findById(Long id);
    Customer findByIdCus(Long id);

    Optional<Customer> findByEmail(String email);

    Optional<GetIdCustomerView> getIdCustomerByIdAccount(Long idAccount);

    Boolean checkMail(String email);

    List<String> getAllEmailCustomer();

}
