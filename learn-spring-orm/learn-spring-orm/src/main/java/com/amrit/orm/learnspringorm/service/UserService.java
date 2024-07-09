package com.amrit.orm.learnspringorm.service;

import com.amrit.orm.learnspringorm.entities.User;

import java.util.List;

public interface UserService {

    public User saveUser( User user);

   public User updateUser(User user , int userId);

   void deleteUser(int userId);

   List<User> getAllUser();

   User getUser(int userId);


}
