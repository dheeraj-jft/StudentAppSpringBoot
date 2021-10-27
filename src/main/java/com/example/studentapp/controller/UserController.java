package com.example.studentapp.controller;

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
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String userDashBoard(Model model, Authentication authentication) {
        String roleString = authentication.getAuthorities().toString();
        model.addAttribute("role", roleString);
        model.addAttribute("username", authentication.getName());
        return "users";
    }

    @GetMapping("/userslist")
    public ResponseEntity<List<UserDto>> getUserList() {
        List<UserDto> userDtoList = userService.getUsersList();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @PostMapping
    public String addUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
        return "fragments/successmodal :: successModalFragment(value='User added successfully')";
    }

    @PutMapping("/{oldname}")
    public String updateUser(@RequestBody UserDto userDto, @PathVariable("oldname") String oldname) {
        userService.updateUser(userDto, oldname);
        return "fragments/successmodal :: successModalFragment(value='User updated successfully')";
    }

    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return "fragments/successmodal :: successModalFragment(value='User with username: "+username+" deleted successfully')";
    }

}
