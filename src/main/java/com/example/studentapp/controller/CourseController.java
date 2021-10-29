package com.example.studentapp.controller;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.dto.CourseDto;
import com.example.studentapp.repositories.StudentRepository;
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
@RequestMapping("course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    StudentRepository studentRepository;

    @GetMapping
    public String getAllCourses(Model model, Authentication authentication) {
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "courses";
    }

    @GetMapping("/details/{courseId}")
    public String getCourseDetails(@PathVariable("courseId") String courseId, Model model, Authentication authentication) {
        Course course = courseService.findByCourseId(courseId);
        model.addAttribute("course", course);
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "courseDetails";
    }

    @GetMapping("/list")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(courseService.getCourseList(), HttpStatus.OK);
    }

    @PostMapping
    public String addCourse(@RequestBody CourseDto courseDto) {
        courseService.addCourse(courseDto);
        return "fragments/successmodal :: successModalFragment(value='Course added successfully')";
    }

    @PutMapping
    public String updateCourse(@RequestBody CourseDto courseDto) {
        courseService.updateCourse(courseDto);
        return "fragments/successmodal :: successModalFragment(value='Course updated successfully')";
    }

    @DeleteMapping("/{courseId}")
    public String deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);
        return "fragments/successmodal :: successModalFragment(value='Course with courseId: " + courseId + " deleted successfully')";
    }
}
