package com.erp.erpsystem.common.dto.request;

import com.erp.erpsystem.projects.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record TaskRequest(
        @NotBlank(message = "Task title is required") String title,

        String description,

        @NotNull(message = "Status is required")
        TaskStatus status,

        LocalDate dueDate,

        Long employeeId
) {}
