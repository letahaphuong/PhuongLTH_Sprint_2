package com.example.phuonglth_sprint_2.service.employee.impl;

import com.example.phuonglth_sprint_2.entity.employee.Employee;
import com.example.phuonglth_sprint_2.repository.employee.IEmployeeRepository;
import com.example.phuonglth_sprint_2.service.employee.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    IEmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void removeFlag(Long id) {

    }
}
