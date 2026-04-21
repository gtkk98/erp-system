package com.erp.erpsystem.hr.repository;

import com.erp.erpsystem.hr.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Spring magically implements this based on the method name
    boolean existsByEmail(String email);
}
