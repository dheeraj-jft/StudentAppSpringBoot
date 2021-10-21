package com.example.studentapp.service;

import com.example.studentapp.datamodel.User;
import com.example.studentapp.repositories.UserRepository;
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
    public User addUser(User user){
        User userModel =popluateData(user);
        return userRepository.save(userModel);
    }

    @Override
    public void updateUser(User user,String oldName){
        User user1= userRepository.findByUsername(oldName);
        user1=popluateData(user,user1);
        userRepository.save(user1);
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser( String username) {
        User user =userRepository.findByUsername(username);
        userRepository.delete(user);
    }

    private User popluateData(User user) {
        User userModel =new User();
        userModel.setPassword(passwordEncoder.encode(user.getPassword()));
        userModel.setUsername(user.getUsername());
        userModel.setRole(user.getRole());
        return userModel;
    }

    private User popluateData(User user, User user1) {
        if(user.getPassword()!="")
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getUsername()!="" && user.getUsername()!=user1.getUsername())
            user1.setUsername(user.getUsername());
        if(user.getRole()!="" && user.getRole()!=user1.getRole())
            user1.setRole(user.getRole());
        return user1;
    }
}
