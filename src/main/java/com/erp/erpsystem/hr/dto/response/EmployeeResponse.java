package com.erp.erpsystem.hr.dto.response;

import com.erp.erpsystem.hr.entity.Role;

import java.time.LocalDate;

public record EmployeeResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Role role,
        LocalDate hireDate
) {}
