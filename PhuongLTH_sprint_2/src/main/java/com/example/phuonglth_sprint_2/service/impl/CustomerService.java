package com.example.phuonglth_sprint_2.service.impl;

import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.repository.ICustomerRepository;
import com.example.phuonglth_sprint_2.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
