package com.novoa.videogames.controller;

import com.novoa.videogames.dto.UserDto;
import com.novoa.videogames.entity.User;
import com.novoa.videogames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    @Autowired
    UserService userService;
    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getUserIterable());
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
        return this.userService.getUserById(userId);
    }
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        return this.userService.saveUser(userDto);
    }
    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto){
        return this.userService.updateUser(userDto);
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") String userId){
        return this.userService.deleteUserById(userId);
    }
}
