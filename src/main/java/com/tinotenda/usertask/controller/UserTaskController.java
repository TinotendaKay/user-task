package com.tinotenda.usertask.controller;

import com.tinotenda.usertask.dao.TaskDao;
import com.tinotenda.usertask.dao.UserDao;
import com.tinotenda.usertask.entities.Task;
import com.tinotenda.usertask.entities.User;
import com.tinotenda.usertask.services.UserTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserTaskController {

    @Autowired
    UserTaskService userTaskService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDao userDao) {
        String transId = UUID.randomUUID().toString();
        log.debug("{} - UserDao: {}", transId, userDao);
        User user = userTaskService.createUser(userDao, transId);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/user/{user_id}/task")
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskDao taskDao, @PathVariable("user_id") Long userId) {
        String transId = UUID.randomUUID().toString();
        log.debug("{} - TaskDao: {}", transId, taskDao);
        Task task = userTaskService.createTask(taskDao, userId, transId);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }
}
