package com.example.studentapp.controller;

import com.example.studentapp.convertor.CourseConvertor;
import com.example.studentapp.datamodel.Course;
import com.example.studentapp.dto.CourseDto;
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
    CourseConvertor convertor;

    @GetMapping()
    public String getAllCourses(Model model, Authentication authentication) {
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "courses";
    }

    @GetMapping("/details/{courseId}")
    public String getCourseDetails(@PathVariable("courseId") String courseId, Model model, Authentication authentication) {
        Course course = courseService.findByCourseId(courseId);
        CourseDto courseDto = convertor.entityToDtoConvertor(course);
        model.addAttribute("course", courseDto);
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "courseDetails";
    }

    @GetMapping("/list")
    public ResponseEntity<List<CourseDto>> getCourses() {
        List<CourseDto> courseDtoList = convertor.entityToDtoListConvertor(courseService.getCourseList());
        return new ResponseEntity<>(courseDtoList, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> addCourse(@RequestBody CourseDto courseDto) {
        Course course = convertor.dtoToEntityConvertor(courseDto);
        courseService.addCourse(course);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Void> updateCourse(@RequestBody CourseDto courseDto) {
        Course course = convertor.dtoToEntityConvertor(courseDto);
        courseService.updateCourse(course);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String courseId) {
        courseService.deleteCourse(courseId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
