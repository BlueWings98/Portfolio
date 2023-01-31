package com.novoa.videogames.service;

import com.novoa.videogames.dto.UserDto;
import com.novoa.videogames.entity.User;
import com.novoa.videogames.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public Iterable<User> getUserIterable(){
        return userRepository.findAll();
    }
    public ResponseEntity<User> getUserById(String userId){
        if(!userExistsById(userId)){
            return new ResponseEntity("There is no user with that Id: " + userId, HttpStatus.NOT_FOUND);
        }
        try{
            return ResponseEntity.ok(userRepository.findById(userId).get());
        } catch (IllegalArgumentException e){
            System.out.println("The given argument was null while trying to fetch it, in User Repository");
        } catch (NoSuchElementException e ){
            System.out.println("There was no element with the given Id: "+ userId +" while trying to fetch by Id User");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<User> saveUser(UserDto userDto){
        if(userExistsById(userDto.getUserId())){
            return new ResponseEntity("The user Id already exists", HttpStatus.BAD_REQUEST);
        }
        User user = new User(
                userDto.getUserId(),
                userDto.getUserName(),
                userDto.getUserRole()
        );
        try {
            return ResponseEntity.ok(userRepository.save(user));
        } catch (IllegalArgumentException | OptimisticLockingFailureException e){
            System.out.println("The given argument was null while trying to save, in User Repository");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<User> updateUser(UserDto userDto){
        if(!userExistsById(userDto.getUserId())){
            return new ResponseEntity("The user with that Id does not exist", HttpStatus.BAD_REQUEST);
        }
        try {
            User user = userRepository.findById(userDto.getUserId()).get();
            user.setUserId(userDto.getUserId());
            user.setUserName(userDto.getUserName());
            user.setUserRole(userDto.getUserRole());
            return ResponseEntity.ok(userRepository.save(user));
        } catch (IllegalArgumentException | OptimisticLockingFailureException e){
            System.out.println("The given argument was null while trying to save, in User Repository");
        } catch (NoSuchElementException e ){
            System.out.println("There was no element with the given Id, while trying to update User");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<?> deleteUserById(String userId){
        if(!userExistsById(userId)){
            return new ResponseEntity("There is no user with that Id: " + userId, HttpStatus.NOT_FOUND);
        }
        try{
            User user = userRepository.findById(userId).get();
            if(!user.equals(Optional.empty())){
                userRepository.deleteById(userId);
                return new ResponseEntity("User deleted successfully", HttpStatus.OK);
            } else {
                System.out.println("There is no such element with Id: " + userId + " In User Repository");
            }
        } catch (NoSuchElementException e){
            System.out.println("There is no such element to delete with id: "+ userId+ " in User Repository.");
        } catch (IllegalArgumentException e ){
            System.out.println("The given argument was null while trying to delete, in User Repository");
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    public boolean userExistsById(String userId){
        try{
            return userRepository.existsById(userId);
        } catch (IllegalArgumentException e){
            System.out.println("The given argument was null while trying to check if that user existed by id.");
        }
        return false;
    }
    public boolean consoleExistsByName(String userName){
        try {
            return userRepository.existsByUserName(userName);
        } catch (IllegalArgumentException e){
            System.out.println("The given argument was null while trying to check if that user existed by userName.");
        }
        return false;
    }
}
