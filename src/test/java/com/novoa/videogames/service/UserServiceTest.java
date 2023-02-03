package com.novoa.videogames.service;

import com.novoa.videogames.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UserServiceTest {
    UserRepository userRepository;
    UserService userService;
    @BeforeEach
    void setUp() {
        this.userRepository = mock(UserRepository.class);
        this.userService = new UserService();
    }
    @Test
    void getUserIterableSuccess() {
    }
    @Test
    void getUserIterableFail() {
    }
    @Test
    void getUserByIdSuccess() {
    }
    @Test
    void getUserByIdFail() {
    }
    @Test
    void saveUserSuccess() {
    }
    @Test
    void saveUserFail() {
    }
    @Test
    void updateUserSuccess() {
    }
    @Test
    void updateUserFail() {
    }
    @Test
    void deleteUserSuccess() {
    }
    @Test
    void deleteUserFail() {
    }
    @Test
    void userExistsByIdSuccess() {
    }
    @Test
    void userExistsByIdFail() {
    }
    @Test
    void consoleExistsByNameSuccess() {
    }
    @Test
    void consoleExistsByNameFail() {
    }
}