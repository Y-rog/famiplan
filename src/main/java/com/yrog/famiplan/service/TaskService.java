package com.yrog.famiplan.service;

import com.yrog.famiplan.entity.Task;

import java.util.List;

public interface TaskService {

    void createTask(Task task);

    List<Task> findAllTasks ();
}
