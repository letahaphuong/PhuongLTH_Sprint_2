package com.example.phuonglth_sprint_2.service.valid.impl;

import com.example.phuonglth_sprint_2.repository.customer.ICustomerRepository;
import com.example.phuonglth_sprint_2.service.customer.ICustomerService;
import com.example.phuonglth_sprint_2.service.valid.IValidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidService implements IValidService {

    @Autowired
    ICustomerRepository customerRepository;
    @Override
    public Boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByPhone(String phone) {
        return customerRepository.existsByPhone(phone);
    }
}
