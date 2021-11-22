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

import java.util.List;
import java.util.Optional;

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

    @Override
    public User updateUser(UserDao userDao, Long userId, String transId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));

        if (userDao.getUserName() != null) {
            user.setUserName(userDao.getUserName());
        }

        if (userDao.getFirstName() != null) {
            user.setFirstName(userDao.getFirstName());
        }

        if (userDao.getLastName() != null) {
            user.setLastName(userDao.getLastName());
        }
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers(String transId) {
        return userRepository.findAll();
    }

    @Override
    public User getUserInfo(Long userId, String transId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found"));
    }

    @Override
    public void updateTask(TaskDao taskDao, Long userId, Long taskId, String transId) {
        Task task = this.getTaskInfo(userId, taskId, transId);

        if (taskDao.getDateTime() != null) {
            task.setDateTime(taskDao.getDateTime());
        }

        if (taskDao.getName() != null) {
            task.setName(taskDao.getName());
        }

        if (taskDao.getDescription() != null) {
            task.setDescription(taskDao.getDescription());
        }
        taskRepository.save(task);

    }

    @Override
    public void deleteTask(Long userId, Long taskId, String transId) {
        Task task = this.getTaskInfo(userId, taskId, transId);
        taskRepository.delete(task);
    }

    @Override
    public Task getTaskInfo(Long userId, Long taskId, String transId) {

        Task task = taskRepository.findByUserIdAndId(userId, taskId);
        if (task == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return task;
    }

    @Override
    public List<Task> getAllTasks(Long userId, String transId) {
        return taskRepository.findAllByUserId(userId);
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
