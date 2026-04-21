package com.erp.erpsystem.hr.dto.request;

import com.erp.erpsystem.hr.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmployeeRequest(

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotNull(message = "Role is required")
        Role role,

        @NotNull(message = "Hire date is required")
        LocalDate hireDate
)
{}
