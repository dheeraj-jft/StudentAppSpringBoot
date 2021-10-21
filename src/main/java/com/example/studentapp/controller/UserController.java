package com.example.studentapp.controller;


import com.example.studentapp.datamodel.User;

import com.example.studentapp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {


    @Autowired
    UserServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
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
    public ResponseEntity<Void> editUserDetails(@RequestBody User user, Principal principal) {
        userService.updateUser(user, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/users")
    public String userDashBoard(Model model, Authentication authentication){
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "users";
    }

    @GetMapping("/users/userslist")
    public ResponseEntity<List<User>> getUsersList(){
        return new ResponseEntity<>(userService.getUsersList(),HttpStatus.OK);
    }

    @PostMapping("/users/register")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users/register/{oldname}")
    public ResponseEntity<Void> updateUser(@RequestBody User user, @PathVariable("oldname") String oldname) {
        userService.updateUser(user,oldname);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("users/delete/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/userrole")
    public ResponseEntity<String> currentUserRole(Authentication principal) {
        return new ResponseEntity<>(principal.getAuthorities().toString(), HttpStatus.OK);
    }

}
