package com.example.studentapp.service.impl;

import com.example.studentapp.datamodel.User;
import com.example.studentapp.dto.UserDto;
import com.example.studentapp.repositories.UserRepository;
import com.example.studentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserDto userDto) {
        User userModel = popluateData(userDto);
        userRepository.save(userModel);
    }

    @Override
    public void updateUser(UserDto userDto, String oldName) {
        User user = userRepository.findByUsername(oldName);
        popluateData(userDto, user);
        userRepository.save(user);
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }

    @Override
    public boolean isExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    private User popluateData(UserDto userDto) {
        User userModel = new User();
        userModel.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userModel.setUsername(userDto.getUsername());
        userModel.setRole(userDto.getRole());
        return userModel;
    }

    private void popluateData(UserDto userDto, User user) {
        if (!userDto.getPassword().equals(""))
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (!userDto.getUsername().equals("") && !userDto.getUsername().equals(user.getUsername()))
            user.setUsername(userDto.getUsername());
        if (!userDto.getRole().equals("") && !userDto.getRole().equals(user.getRole()))
            user.setRole(userDto.getRole());
    }
}
