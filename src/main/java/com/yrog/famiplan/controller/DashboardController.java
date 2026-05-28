package com.yrog.famiplan.controller;

import com.yrog.famiplan.entity.Task;
import com.yrog.famiplan.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final TaskService taskService;
    public DashboardController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getDashboard (Model model) {
        model.addAttribute("tasks", taskService.findAllTasks());
        model.addAttribute("task", new Task());
        return "dashboard";
    }

    @PostMapping
    public String createTask(@Valid @ModelAttribute Task task, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tasks", taskService.findAllTasks());
            return "redirect:/dashboard";
        }
        taskService.createTask(task);
        return "redirect:/dashboard";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/dashboard";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Task> task = taskService.getTaskById(id);
        if (task.isEmpty()) {
            return "redirect:/dashboard";
        }
        model.addAttribute("task", task.get());
        return "edit-task";
    }

    @PatchMapping("/{id}/edit")
    public String editTask (@PathVariable Long id, @ModelAttribute Task task){
        taskService.updateTask(id, task);
        return "redirect:/dashboard";
    }
}
