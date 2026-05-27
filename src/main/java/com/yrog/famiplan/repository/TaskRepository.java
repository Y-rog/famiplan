package com.yrog.famiplan.repository;

import com.yrog.famiplan.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
