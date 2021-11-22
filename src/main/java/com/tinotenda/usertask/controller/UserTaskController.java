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
import java.util.List;
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
        log.debug("{} - createTask: {}", transId, taskDao);
        Task task = userTaskService.createTask(taskDao, userId, transId);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody UserDao userDao, @PathVariable("id") Long userId) {
        String transId = UUID.randomUUID().toString();
        log.debug("{} - updateUser: {}", transId, userDao);
        userTaskService.updateUser(userDao, userId, transId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> listAllUsers() {
        String transId = UUID.randomUUID().toString();
        log.debug("{} - listAllUsers", transId);

        List<User> allUsers = userTaskService.getAllUsers(transId);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserInfo(@PathVariable("id") Long userId) {
        String transId = UUID.randomUUID().toString();
        log.debug("{} - getUserInfo userId: {}", transId, userId);

        User userInfo = userTaskService.getUserInfo(userId, transId);

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }

    @GetMapping("/user/{user_id}/task")
    public ResponseEntity<List<Task>> listAllUserTasks(@PathVariable("user_id") Long userId) {

        String transId = UUID.randomUUID().toString();
        log.debug("{} - listAllUserTasks userId: {}", transId, userId);

        List<Task> allTasks = userTaskService.getAllTasks(userId, transId);
        return new ResponseEntity<>(allTasks, HttpStatus.OK);

    }

    @GetMapping("/user/{user_id}/task/{task_id}")
    public ResponseEntity<Task> getTaskInfo(@PathVariable("user_id") Long userId, @PathVariable("task_id") Long taskId) {
        String transId = UUID.randomUUID().toString();
        log.debug("{} - getTaskInfo userId: {}, taskId: {}", transId, userId, taskId);

        Task taskInfo = userTaskService.getTaskInfo(userId, taskId, transId);

        return new ResponseEntity<>(taskInfo, HttpStatus.OK);
    }

    @DeleteMapping("/user/{user_id}/task/{task_id}")
    public ResponseEntity<Void> deleterUserTask(@PathVariable("user_id") Long userId, @PathVariable("task_id") Long taskId) {
        String transId = UUID.randomUUID().toString();
        log.debug("{} - deleterUserTask userId: {}, taskId: {}", transId, userId, taskId);

        userTaskService.deleteTask(userId, taskId, transId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/user/{user_id}/task/{task_id}")
    public ResponseEntity<Void> updateUserTask(@PathVariable("user_id") Long userId, @PathVariable("task_id") Long taskId, @Valid @RequestBody TaskDao taskDao) {
        String transId = UUID.randomUUID().toString();
        log.debug("{} - updateUserTask  request: {}, userId: {}, taskId: {}", transId, taskDao, userId, taskId);

        userTaskService.deleteTask(userId, taskId, transId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
