package com.erp.erpsystem.controller;

import com.erp.erpsystem.common.dto.request.TaskRequest;
import com.erp.erpsystem.common.dto.response.TaskResponse;
import com.erp.erpsystem.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(
            @Valid @RequestBody TaskRequest request) {
        return taskService.createTask(request);
    }

    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/employee/{employeeId}")
    public List<TaskResponse> getTasksByEmployee(@PathVariable("employeeId") Long employeeId) {
        return taskService.getTasksByEmployee(employeeId);
    }
}
