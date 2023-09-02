package com.lcwd.user.service.service;

import com.lcwd.user.service.entity.User;

import java.util.List;

public interface UserService {

    //save user
    User saveUser(User user);

    //get all user
    List<User> showUser();

    //get a single user
    User getUserById(String userId);



}
