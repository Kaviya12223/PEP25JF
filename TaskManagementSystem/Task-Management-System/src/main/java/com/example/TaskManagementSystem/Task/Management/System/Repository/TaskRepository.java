package com.example.TaskManagementSystem.Task.Management.System.Repository;

import com.example.TaskManagementSystem.Task.Management.System.Entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
}
