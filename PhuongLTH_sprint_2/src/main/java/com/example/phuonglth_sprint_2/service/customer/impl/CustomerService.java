package com.example.phuonglth_sprint_2.service.customer.impl;

import com.example.phuonglth_sprint_2.dto.customer.CustomerView;
import com.example.phuonglth_sprint_2.dto.customer.GetIdCustomerView;
import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.repository.customer.ICustomerRepository;
import com.example.phuonglth_sprint_2.service.customer.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    ICustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Page<CustomerView> getAllCustomer(String name, String email, Pageable pageable) {
        return customerRepository.getAllCustomer(name, email, pageable);
    }

    @Override
    public void removeFlag(Long id) {
        customerRepository.removeFlag(id);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer findByIdCus(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Optional<GetIdCustomerView> getIdCustomerByIdAccount(Long idAccount) {
        return customerRepository.getIdCustomerByIdAccount(idAccount);
    }

    @Override
    public Boolean checkMail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public List<String> getAllEmailCustomer() {
        return customerRepository.getAllEmailCustomer();
    }
}
