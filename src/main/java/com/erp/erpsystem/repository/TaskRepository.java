package com.erp.erpsystem.repository;

import com.erp.erpsystem.projects.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Spring Data JPA magically writes the SQL for this based on the method name!
    List<Task> findByAssigneeId(Long employeeId);
}
