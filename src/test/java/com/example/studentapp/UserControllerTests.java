package com.example.studentapp;

import com.example.studentapp.controller.UserController;
import com.example.studentapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    UserService userService;

}
