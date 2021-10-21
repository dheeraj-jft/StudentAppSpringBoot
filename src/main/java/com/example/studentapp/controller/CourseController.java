package com.example.studentapp.controller;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.datamodel.Student;
import com.example.studentapp.repositories.CourseRepository;
import com.example.studentapp.service.CourseService;
import com.example.studentapp.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/courses")
    public String getAllCourses(Model model, Authentication authentication) {
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "courses";
    }

    @GetMapping("/course/list")
    public ResponseEntity<List<Course>> getCourses(){
        return new ResponseEntity<>(courseService.getCourseList(),HttpStatus.OK);
    }

    @PostMapping("/course/save")
    public ResponseEntity<Void> addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
