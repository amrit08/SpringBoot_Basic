package com.amrit.orm.learnspringorm.service.impl;

import com.amrit.orm.learnspringorm.entities.User;
import com.amrit.orm.learnspringorm.repositories.UserRepository;
import com.amrit.orm.learnspringorm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        logger.info("User saved : {}",savedUser.getId());
        return savedUser;
    }

    @Override
    public User updateUser(User user, int userId) {
        // 1. get user from database
        User oldUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with given id is not found "));
        oldUser.setName(user.getName());
        oldUser.setCity(user.getCity());
        oldUser.setAge(user.getAge());

        // 2. update user
        User updatedUser = userRepository.save(oldUser);


        return updatedUser;
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user is not found with given id "));
        userRepository.delete(user);
        logger.info("user deleted");
    }

    //to get all user
    @Override
    public List<User> getAllUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    // get single user with given id
    @Override
    public User getUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new RuntimeException("user with given id not found"));
        return user;
    }
}
