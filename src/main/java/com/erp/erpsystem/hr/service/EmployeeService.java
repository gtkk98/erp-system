package com.erp.erpsystem.hr.service;

import com.erp.erpsystem.common.exception.ResourceAlreadyExistsException;
import com.erp.erpsystem.hr.dto.request.EmployeeRequest;
import com.erp.erpsystem.hr.dto.response.EmployeeResponse;
import com.erp.erpsystem.hr.entity.Employee;
import com.erp.erpsystem.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        if (employeeRepository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistsException("Email already in use" + request.email());
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

    @Transactional
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setEmail(request.email());
        employee.setRole(request.role());
        employee.setHireDate(request.hireDate());

        return mapToResponse(employee);
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
