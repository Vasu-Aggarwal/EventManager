package com.bookevent.BookEventManager.services.impl;

import com.bookevent.BookEventManager.entities.User;
import com.bookevent.BookEventManager.exceptions.BadRequest;
import com.bookevent.BookEventManager.exceptions.ResourceNotFoundException;
import com.bookevent.BookEventManager.repositories.UserRepository;
import com.bookevent.BookEventManager.services.UserService;
import com.bookevent.BookEventManager.utils.dtos.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto addUser(UserDto userDto) {
        userDto.setUser_id(UUID.randomUUID().toString());
        userDto.setUsername(userDto.getName().replaceAll("\\s","")+ new Random().nextInt(9999));
        if (this.userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new BadRequest(userDto.getEmail());
        } else {
            User user = this.modelMapper.map(userDto, User.class);
            return this.modelMapper.map(this.userRepository.save(user), UserDto.class);
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = this.userRepository.findById(userDto.getUser_id()).orElseThrow(()-> new ResourceNotFoundException("User does not exists"));
        if (this.userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new BadRequest(userDto.getEmail(), " already exists. Kindly, enter new email to update details");
        } else {
            if (userDto.getEmail() == null){
                user.setEmail(user.getEmail());
            } else {
                user.setEmail(userDto.getEmail());
            }
            user.setName(userDto.getName());
            user.setPassword(userDto.getPassword());
            User updatedUser = this.userRepository.save(user);
            return this.modelMapper.map(updatedUser, UserDto.class);
        }

    }

    @Override
    public void deleteUser(String user_id) {
        User user = this.userRepository.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User does not exists"));
        this.userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream()
                .map((user)-> this.modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(String user_id) {
        User user = this.userRepository.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("User does not exists"));
        return this.modelMapper.map(user, UserDto.class);
    }
}
