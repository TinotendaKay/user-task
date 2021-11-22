package com.tinotenda.usertask.services;

import com.tinotenda.usertask.dao.TaskDao;
import com.tinotenda.usertask.dao.UserDao;
import com.tinotenda.usertask.entities.Task;
import com.tinotenda.usertask.entities.User;

public interface UserTaskService {
    User createUser(UserDao userDao, String transId);

    Task createTask(TaskDao taskDao, Long userId, String transId);
}
