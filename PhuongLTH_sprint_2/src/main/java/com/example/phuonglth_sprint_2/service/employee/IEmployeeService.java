package com.example.phuonglth_sprint_2.service.employee;

import com.example.phuonglth_sprint_2.entity.employee.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> findAll();

    Optional<Employee> findById(Long id);

    void save(Employee employee);

    void remove(Long id);

//    Page<T> searchName(String searchByName, Pageable pageable);

    void removeFlag(Long id);
}
