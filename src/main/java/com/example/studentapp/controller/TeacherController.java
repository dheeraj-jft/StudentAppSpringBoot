package com.example.studentapp.controller;

import com.example.studentapp.datamodel.Teacher;
import com.example.studentapp.dto.CourseDto;
import com.example.studentapp.dto.TeacherDto;
import com.example.studentapp.service.CourseService;
import com.example.studentapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseService courseService;

    @GetMapping
    public String getTeachers(Model model, Authentication authentication) {
        model.addAttribute("courseList", courseService.getCourseList());
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "teachers";
    }
    @GetMapping("/list")
    public ResponseEntity<List<Teacher>>getAllTeachers(){
        return  new ResponseEntity<>(teacherService.getTeacherList(), HttpStatus.OK);
    }

    @PostMapping
    public String addTeacher(@RequestBody TeacherDto teacherDto) {
        teacherService.addTeacher(teacherDto);
        return "fragments/successmodal :: successModalFragment(value='Teacher added successfully')";
    }


    @PutMapping
    public String updateTeacher(@RequestBody TeacherDto teacherDto) {
        teacherService.updateTeacher(teacherDto);
        return "fragments/successmodal :: successModalFragment(value='Course updated successfully')";
    }

    @DeleteMapping("/{teacherId}")
    public String deleteCourse(@PathVariable String teacherId) {
        teacherService.deleteTeacher(teacherId);
        return "fragments/successmodal :: successModalFragment(value='Course with courseId: " + teacherId + " deleted successfully')";
    }


}
