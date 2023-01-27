package com.novoa.videogames.controller;

import com.novoa.videogames.entity.User;
import com.novoa.videogames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getUserIterable());
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        if(!userService.userExistsById(userId)){
            return new ResponseEntity("There is no user with that Id: " + userId, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
