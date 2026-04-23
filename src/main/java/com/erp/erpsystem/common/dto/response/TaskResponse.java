package com.erp.erpsystem.common.dto.response;

import com.erp.erpsystem.projects.entity.TaskStatus;

import java.time.LocalDate;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        LocalDate dueDate,
        Long assigneedId,
        String assigneeName
) {}
