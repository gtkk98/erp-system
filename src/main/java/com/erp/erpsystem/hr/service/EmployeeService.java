package com.erp.erpsystem.hr.service;

import com.erp.erpsystem.hr.dto.request.EmployeeRequest;
import com.erp.erpsystem.hr.dto.response.EmployeeResponse;
import com.erp.erpsystem.hr.entity.Employee;
import com.erp.erpsystem.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        if (employeeRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email already in use" + request.email());
        }
        // Map DTO to Entity using the Lombok Builder we added earlier
        Employee employee = Employee.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .role(request.role())
                .hireDate(request.hireDate())
                .build();
        // Save to DB
        Employee savedEmployee = employeeRepository.save(employee);
        //  Map entity back to response dto
        return mapToResponse(savedEmployee);
    }

    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getRole(),
                employee.getHireDate()
        );
    }
}
