package com.example.studentapp.service;

import com.example.studentapp.datamodel.User;
import com.example.studentapp.dto.UserDto;

import java.util.List;

public interface UserService {
    void addUser(UserDto userDto);

    void updateUser(UserDto userDto, String oldName);

    List<User> getUsersList();

    void deleteUser(String username);

    boolean isExists(String username);

    User findUserByEmailAddress(String emailAddress);


}
