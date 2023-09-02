package com.lcwd.user.service.service.impl;

import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.spi.ResourceBundleControlProvider;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public User saveUser(User user) {
        //generate a unique id
       String randomUserId = UUID.randomUUID().toString();
       user.setUserId(randomUserId);
        return repository.save(user);
    }

    @Override
    public List<User> showUser() {
        return repository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return repository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("Requested Resource Is Not Available"+userId));
    }
}
