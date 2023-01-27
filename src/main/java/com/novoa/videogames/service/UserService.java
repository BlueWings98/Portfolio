package com.novoa.videogames.service;

import com.novoa.videogames.dto.UserDto;
import com.novoa.videogames.entity.User;
import com.novoa.videogames.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
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
    public User getUserById(String userId){
        try{
            return userRepository.findById(userId).get();
        } catch (IllegalArgumentException e){
            System.out.println("The given argument was null while trying to fetch it, in User Repository");
        } catch (NoSuchElementException e ){
            System.out.println("There was no element with the given Id: "+ userId +" while trying to fetch by Id User");
        }
        return new User();
    }
    public User saveUser(UserDto userDto){
        User user = new User(
                userDto.getUserId(),
                userDto.getUserName(),
                userDto.getUserRole()
        );
        try {
            return userRepository.save(user);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e){
            System.out.println("The given argument was null while trying to save, in User Repository");
        }
        return new User();
    }
    public User updateUser(UserDto userDto){
        try {
            User user = userRepository.findById(userDto.getUserId()).get();
            user.setUserId(userDto.getUserId());
            user.setUserName(userDto.getUserName());
            user.setUserRole(userDto.getUserRole());
            return userRepository.save(user);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e){
            System.out.println("The given argument was null while trying to save, in User Repository");
        } catch (NoSuchElementException e ){
            System.out.println("There was no element with the given Id, while trying to update User");
        }
        return new User();
    }
    public void deleteUser(String userId){
        try{
            User user = userRepository.findById(userId).get();
            if(!user.equals(Optional.empty())){
                userRepository.deleteById(userId);
            } else {
                System.out.println("There is no such element with Id: " + userId + " In User Repository");
            }
        } catch (NoSuchElementException e){
            System.out.println("There is no such element to delete with id: "+ userId+ " in User Repository.");
        } catch (IllegalArgumentException e ){
            System.out.println("The given argument was null while trying to delete, in User Repository");
        }
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
