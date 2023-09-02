package com.lcwd.user.service.controllers;

import com.lcwd.user.service.entity.User;
import com.lcwd.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserContoller {
    @Autowired
    private UserService service;

    //Create user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
      User user1= service.saveUser(user);
      return  ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user=service.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List <User> allUser=service.showUser();
        return ResponseEntity.ok(allUser);
    }


}
