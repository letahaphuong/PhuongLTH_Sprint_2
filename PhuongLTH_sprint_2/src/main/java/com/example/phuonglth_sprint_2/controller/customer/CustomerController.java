package com.example.phuonglth_sprint_2.controller.customer;

import com.example.phuonglth_sprint_2.dto.customer.CustomerDto;
import com.example.phuonglth_sprint_2.dto.customer.CustomerView;
import com.example.phuonglth_sprint_2.entity.customer.Customer;
import com.example.phuonglth_sprint_2.service.customer.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("api/customer")
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping
    public ResponseEntity<Page<CustomerView>> getAllCustomer(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String email,
            @PageableDefault(page = 0, size = 3) Pageable pageable
    ) {
        Page<CustomerView> customerViews = customerService.getAllCustomer(name, email, pageable);
        if (customerViews.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerViews, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            customerService.removeFlag(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(customer.get(),HttpStatus.OK);
        }
    }

    @PatchMapping("/update-customer")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
        if (customerService.findById(customerDto.getIdCustomer()) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        customerService.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
