package com.erp.erpsystem.service;

import com.erp.erpsystem.common.dto.request.TaskRequest;
import com.erp.erpsystem.common.dto.response.TaskResponse;
import com.erp.erpsystem.common.exception.ResourceNotFoundException;
import com.erp.erpsystem.hr.entity.Employee;
import com.erp.erpsystem.hr.repository.EmployeeRepository;
import com.erp.erpsystem.projects.entity.Task;
import com.erp.erpsystem.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public TaskResponse createTask(TaskRequest request) {
        Employee assignee = null;

        // If an employeeId was provided, look them up in the database
        if (request.employeeId() != null) {
            assignee = employeeRepository.findById(request.employeeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + request.employeeId()));
        }

        // Build the task entity
        Task task = Task.builder()
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .dueDate(request.dueDate())
                .assignee(assignee)
                .build();

        Task savedTask = taskRepository.save(task);
        return mapToResponse(savedTask);
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Helper method to convert Task entity to TaskResponse DTO
    private TaskResponse mapToResponse(Task task) {
        Long assigneeId = null;
        String assigneeName = null;

        if (task.getAssignee() != null) {
            assigneeId = task.getAssignee().getId();
            assigneeName = task.getAssignee().getFirstName() +  " " + task.getAssignee().getLastName();
        }

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getDueDate(),
                assigneeId,
                assigneeName
        );
    }
}
