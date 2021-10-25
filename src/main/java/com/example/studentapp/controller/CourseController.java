package com.example.studentapp.controller;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/courseDetails/{courseId}")
    public String getCourseDetails(@PathVariable("courseId") String courseId, Model model, Authentication authentication) {
        Course course= courseService.findByCourseId(courseId);
        model.addAttribute("course",course);
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "courseDetails";
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
    @PutMapping("/course/save")
    public ResponseEntity<Void> updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @DeleteMapping("/course/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
