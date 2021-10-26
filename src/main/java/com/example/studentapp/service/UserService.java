package com.example.studentapp.service;

import com.example.studentapp.datamodel.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    void updateUser(User user, String oldName);

    List<User> getUsersList();

    void deleteUser(String username);
}
