package com.novoa.videogames.controller;

import com.novoa.videogames.dto.UserDto;
import com.novoa.videogames.entity.User;
import com.novoa.videogames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        if(userService.userExistsById(userDto.getUserId())){
            return new ResponseEntity("The user Id already exists", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.saveUser(userDto));
    }
    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody UserDto userDto){
        if(!userService.userExistsById(userDto.getUserId())){
            return new ResponseEntity("The user with that Id does not exist", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.updateUser(userDto));
    }
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") String userId){
        if(!userService.userExistsById(userId)){
            return new ResponseEntity("There is no user with that Id: " + userId, HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(userId);
        return new ResponseEntity("User deleted successfully", HttpStatus.OK);
    }
}
