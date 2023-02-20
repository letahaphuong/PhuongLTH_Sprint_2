package com.example.phuonglth_sprint_2.repository.employee;

import com.example.phuonglth_sprint_2.entity.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee,Long> {
}
