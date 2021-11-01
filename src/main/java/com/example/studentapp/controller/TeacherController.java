package com.example.studentapp.controller;

import com.example.studentapp.datamodel.Teacher;
import com.example.studentapp.dto.TeacherDto;
import com.example.studentapp.repositories.TeacherRepository;
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

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping
    public String getTeachers(Model model, Authentication authentication) {
        model.addAttribute("courseList", courseService.getCourseList());
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "teachers";
    }

    @GetMapping("/list")
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return new ResponseEntity<>(teacherService.getTeacherList(), HttpStatus.OK);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getStudent(@PathVariable("teacherId") String teacherId) {
        Teacher teacher = teacherRepository.findByTeacherId(teacherId);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @GetMapping("/details/{teacherId}")
    public String getStudentDetails(@PathVariable("teacherId") String teacherId, Model model) {
        model.addAttribute("teacher", teacherRepository.findByTeacherId(teacherId));
        return "teacherDetails";
    }

    @PostMapping
    public String addTeacher(@RequestBody TeacherDto teacherDto) {
        teacherService.addTeacher(teacherDto);
        return "fragments/successmodal :: successModalFragment(value='Teacher added successfully')";
    }


    @PutMapping
    public String updateTeacher(@RequestBody TeacherDto teacherDto) {
        teacherService.updateTeacher(teacherDto);
        return "fragments/successmodal :: successModalFragment(value='Teacher updated successfully')";
    }

    @DeleteMapping("/{teacherId}")
    public String deleteCourse(@PathVariable String teacherId) {
        teacherService.deleteTeacher(teacherId);
        return "fragments/successmodal :: successModalFragment(value='Teacher with teacherId: " + teacherId + " deleted successfully')";
    }


}
