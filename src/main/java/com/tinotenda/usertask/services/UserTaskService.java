package com.tinotenda.usertask.services;

import com.tinotenda.usertask.dao.TaskDao;
import com.tinotenda.usertask.dao.UserDao;
import com.tinotenda.usertask.entities.Task;
import com.tinotenda.usertask.entities.User;

import java.util.List;

public interface UserTaskService {
    User createUser(UserDao userDao, String transId);

    Task createTask(TaskDao taskDao, Long userId, String transId);

    User updateUser(UserDao userDao, Long userId, String transId);

    List<User> getAllUsers(String transId);

    User getUserInfo(Long userId, String transId);

    void updateTask(TaskDao taskDao, Long userId,Long taskId, String transId);

    void deleteTask(Long userId, Long taskId, String transId);


    Task getTaskInfo(Long userId, Long taskId, String transId);

    List<Task> getAllTasks(Long userId, String transId);

}
