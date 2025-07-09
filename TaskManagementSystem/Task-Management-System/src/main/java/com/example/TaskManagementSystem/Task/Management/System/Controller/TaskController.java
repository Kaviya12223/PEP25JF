package com.example.TaskManagementSystem.Task.Management.System.Controller;

import com.example.TaskManagementSystem.Task.Management.System.Entity.TaskEntity;
import com.example.TaskManagementSystem.Task.Management.System.Service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping("/get")
    public String home(Model model) {
        model.addAttribute("tasks", TaskService.getAllTasks());
        model.addAttribute("newTask", new TaskEntity());
        return "index"; // assuming you have index.html
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute TaskEntity newTask) {
        TaskService.createTask(newTask);
        return "redirect:/get";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        TaskService.deleteTaskById(id);
        return "redirect:/get";
    }

    @GetMapping("/deleteAll")
    public String deleteAllTasks() {
        TaskService.deleteAllTasks();
        return "redirect:/get";
    }

    @GetMapping("/complete/{id}")
    public String markAsCompleted(@PathVariable int id) {
        TaskService.markCompleted(id);
        return "redirect:/get";
    }
}
