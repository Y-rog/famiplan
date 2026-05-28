package com.yrog.famiplan.service.impl;

import com.yrog.famiplan.entity.Task;
import com.yrog.famiplan.repository.TaskRepository;
import com.yrog.famiplan.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public List<Task> findAllTasks() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void updateTask(Long id, Task updatedTask) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            Task existingTask = task.get();
            existingTask.setDescription(updatedTask.getDescription() );
            taskRepository.save(existingTask);
        }
    }
}
