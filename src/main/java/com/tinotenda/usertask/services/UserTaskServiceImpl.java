package com.tinotenda.usertask.services;

import com.tinotenda.usertask.dao.TaskDao;
import com.tinotenda.usertask.dao.UserDao;
import com.tinotenda.usertask.entities.Task;
import com.tinotenda.usertask.entities.User;
import com.tinotenda.usertask.repos.TaskRepository;
import com.tinotenda.usertask.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserTaskServiceImpl implements UserTaskService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public User createUser(UserDao userDao, String transId) {
        User user = userRepository.save(mapUserDaoToEntity(userDao));
        return user;
    }

    @Override
    public Task createTask(TaskDao taskDao, Long userId, String transId) {
        User user = userRepository.getById(userId);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
        }
        Task task = mapTaskDaoToEntity(taskDao);
        task.setUser(user);
        return taskRepository.save(task);
    }

    private User mapUserDaoToEntity(UserDao userDao) {
        return User.builder()
                .firstName(userDao.getFirstName())
                .lastName(userDao.getLastName())
                .userName(userDao.getUserName())
                .build();
    }

    private Task mapTaskDaoToEntity(TaskDao taskDao) {
        return Task.builder()
                .description(taskDao.getDescription())
                .name(taskDao.getName())
                .dateTime(taskDao.getDateTime())
                .build();
    }
}
