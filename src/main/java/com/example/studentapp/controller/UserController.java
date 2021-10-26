package com.example.studentapp.controller;

import com.example.studentapp.convertor.UserConvertor;
import com.example.studentapp.datamodel.User;
import com.example.studentapp.dto.UserDto;
import com.example.studentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserConvertor convertor;

    @GetMapping("/users")
    public String userDashBoard(Model model, Authentication authentication) {
        String roleString = authentication.getAuthorities().toString();
        model.addAttribute("role", roleString);
        return "users";
    }

    @GetMapping("/users/userslist")
    public ResponseEntity<List<UserDto>> getUserList() {
        List<UserDto> userDtoList = convertor.entityToDtoListConvertor(userService.getUsersList());
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @PostMapping("/users/register")
    public ResponseEntity<Void> addUser(@RequestBody UserDto userDto) {
        User user= convertor.dtoToEntityConvertor(userDto);
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users/register/{oldname}")
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto, @PathVariable("oldname") String oldname) {
        User user = convertor.dtoToEntityConvertor(userDto);
        userService.updateUser(user, oldname);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("users/delete/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
