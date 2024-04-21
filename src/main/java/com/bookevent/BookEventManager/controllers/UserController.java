package com.bookevent.BookEventManager.controllers;

import com.bookevent.BookEventManager.payloads.responses.ApiResponse;
import com.bookevent.BookEventManager.services.UserService;
import com.bookevent.BookEventManager.utils.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    //CREATE USER
    @PostMapping("/addUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        UserDto newUser = this.userService.addUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    //UPDATE USER
    @PutMapping("/updateUser")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userUpdateDto){
        UserDto updatedUser = this.userService.updateUser(userUpdateDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //DELETE USER
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted successfully", true, 1), HttpStatus.OK);
    }

    //GET ALL USERS
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = this.userService.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    //GET USER BY ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId){
        UserDto userDto = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
