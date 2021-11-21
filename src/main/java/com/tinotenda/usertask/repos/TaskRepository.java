package com.tinotenda.usertask.repos;

import com.tinotenda.usertask.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
