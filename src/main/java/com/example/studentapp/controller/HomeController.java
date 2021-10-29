package com.example.studentapp.controller;

import com.example.studentapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    CourseService courseService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("courseList", courseService.getCourseList());
        return "dashboard";
    }

}
