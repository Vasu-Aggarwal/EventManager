package com.bookevent.BookEventManager.services;

import com.bookevent.BookEventManager.utils.dtos.UserDto;

import java.util.List;

public interface UserService {

    //Create user
    UserDto addUser(UserDto userDto);

    //Update user
    UserDto updateUser(UserDto userDto);

    //Delete user --> ADMIN
    void deleteUser(String user_id);

    //GET ALL USERS ---> ADMIN
    List<UserDto> getAllUsers();

    //GET USER BY ID ----> ADMIN
    UserDto getUserById(String user_id);
}
