package com.yrog.famiplan.controller;

import com.yrog.famiplan.entity.Task;
import com.yrog.famiplan.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String createTask(@Valid @ModelAttribute Task task, BindingResult result) {
        if (result.hasErrors()) {
            return "dashboard";
        }
        taskService.createTask(task);
        return "redirect:/dashboard";
    }
}
