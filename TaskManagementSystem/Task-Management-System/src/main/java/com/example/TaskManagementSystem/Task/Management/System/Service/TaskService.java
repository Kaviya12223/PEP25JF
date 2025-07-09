package com.example.TaskManagementSystem.Task.Management.System.Service;

import com.example.TaskManagementSystem.Task.Management.System.Entity.TaskEntity;
import com.example.TaskManagementSystem.Task.Management.System.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    private static final List<TaskEntity> taskList = new ArrayList<>();
    private static int currentId = 1;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public static List<TaskEntity> getAllTasks() {
        return taskList;
    }

    public static void createTask(TaskEntity task) {
        task.setId(currentId++);
        taskList.add(task);
    }

    public static void deleteTaskById(int id) {
        taskList.removeIf(task -> task.getId() == id);
    }

    public static void deleteAllTasks() {
        taskList.clear();
    }

    public static void markCompleted(int id) {
        for (TaskEntity task : taskList) {
            if (task.getId() == id) {
                task.setCompleted(true);
                break;
            }
        }
    }
}
