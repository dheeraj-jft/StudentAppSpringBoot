package com.example.studentapp.controller;


import com.example.studentapp.dto.UserDto;
import com.example.studentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "dashboard";
    }

    @GetMapping("/edit/profile")
    public String editProfile(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "editprofile";
    }

    @PutMapping("/edit/profile")
    public ResponseEntity<Void> editUserDetails(@RequestBody UserDto userDto, Principal principal) {
        userService.updateUser(userDto, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
