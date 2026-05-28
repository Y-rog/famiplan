package com.yrog.famiplan.service;

import com.yrog.famiplan.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    void createTask(Task task);

    List<Task> findAllTasks ();

    void deleteTask(Long id);

    Optional<Task> getTaskById(Long id);

    void updateTask (Long id, Task task);
}
