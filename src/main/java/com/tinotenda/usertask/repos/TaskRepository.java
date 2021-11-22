package com.tinotenda.usertask.repos;

import com.tinotenda.usertask.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByUserId(Long userId);

    Task findByUserIdAndId(Long userId, Long taskId);

}
