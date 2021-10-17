package com.example.studentapp.service;

import com.example.studentapp.datamodel.CustomUserDetails;
import com.example.studentapp.datamodel.User;
import com.example.studentapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("User not found!!");
        }
        return new CustomUserDetails(user);
    }

    public User saveUser(User user){
        User userModel =popluateData(user);
        return userRepository.save(userModel);
    }
    public void updateUser(User user,String oldName){
        User userModel =popluateData(user);
        userRepository.updateUserDetails(userModel.getUsername(),userModel.getPassword(), oldName);
    }

    private User popluateData(User user) {
        User userModel =new User();
        userModel.setPassword(passwordEncoder.encode(user.getPassword()));
        userModel.setUsername(user.getUsername());
        userModel.setRole(user.getRole());
        return userModel;
    }

}
