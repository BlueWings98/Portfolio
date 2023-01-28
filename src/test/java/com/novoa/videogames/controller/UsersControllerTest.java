package com.novoa.videogames.controller;

import com.novoa.videogames.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class UsersControllerTest {
    UserService userService;
    UsersController usersController;
    @BeforeEach
    void setUp() {
        this.userService = mock(UserService.class);
        this.usersController = new UsersController();
    }
    @Test
    void getAllUsersSuccess() {
    }
    @Test
    void getUserByIdSuccess() {
    }
    @Test
    void createUserSuccess() {
    }
    @Test
    void updateUserSuccess() {
    }
    @Test
    void deleteUserByIdSuccess() {
    }
}