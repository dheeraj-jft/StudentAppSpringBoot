package com.example.studentapp.service.impl;

import com.example.studentapp.datamodel.User;
import com.example.studentapp.dto.UserDto;
import com.example.studentapp.enums.Provider;
import com.example.studentapp.exception.EmailIdAlreadyExistsException;
import com.example.studentapp.repositories.UserRepository;
import com.example.studentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    @Override
    public User findUserByEmailAddress(String emailAddress) {
        User user = userRepository.findByEmailAddress(emailAddress);
        return user;

    }

    private User popluateData(UserDto userDto) {
        User userModel = new User();
        User user1 = userRepository.findByUsername(userDto.getUsername());
        if (user1 != null) {
            throw new DuplicateKeyException("username already used, user different username");
        }
        userModel.setUsername(userDto.getUsername());
        userModel.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userModel.setRole(userDto.getRole());
        if (userDto.getEmailAddress() != "" && Objects.nonNull(userDto.getEmailAddress())) {
            checkEmailValidation(userDto, userModel);
        }

        return userModel;
    }

    private void popluateData(UserDto userDto, User user) {
        if (!userDto.getPassword().equals(""))
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (!userDto.getUsername().equals("") && !userDto.getUsername().equals(user.getUsername()))
            user.setUsername(userDto.getUsername());
        if (!userDto.getRole().equals("") && !userDto.getRole().equals(user.getRole()))
            user.setRole(userDto.getRole());
        if (!userDto.getEmailAddress().equals(user.getEmailAddress())) {
            checkEmailValidation(userDto, user);
        }
    }

    private void checkEmailValidation(UserDto userDto, User user) {
        User user2 = userRepository.findByEmailAddress(userDto.getEmailAddress());
        if (user2 != null) {
            throw new EmailIdAlreadyExistsException(userDto.getEmailAddress() + " is already used by user : " + user2.getUsername());
        }

        if (userDto.getEmailAddress() != "") {
            user.setOAuthEnabled(true);
            user.setEmailAddress(userDto.getEmailAddress());
            user.setProvider(Provider.GOOGLE);
        } else {
            user.setOAuthEnabled(false);
            user.setEmailAddress(null);
            user.setProvider(null);
        }
    }
}
