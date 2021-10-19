package com.example.studentapp.controller;

import com.example.studentapp.datamodel.Student;
import com.example.studentapp.datamodel.User;
import com.example.studentapp.repositories.StudentRepository;
import com.example.studentapp.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/")
    public String home(Model model, Authentication authentication) {
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "dashboard";
    }

    @GetMapping("/edit/profile")
    public String editProfile(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "editprofile";
    }

    @PutMapping("/edit/profile/update")
    public ResponseEntity<Void> editUserDetails(@RequestBody User user, Principal principal) {
        customUserDetailsService.updateUser(user, principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/userrole")
    public ResponseEntity<String> currentUserName(Authentication principal) {
        return new ResponseEntity<>(principal.getAuthorities().toString(), HttpStatus.OK);
    }

    @PostMapping("/student/add")
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/studentlist")
    public ResponseEntity<List<Student>> getStudentList() {
        return new ResponseEntity<>(studentRepository.findAll(Sort.by(Sort.Direction.ASC,"rollno")), HttpStatus.OK);
    }

    @DeleteMapping("/student/delete/{rollno}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer rollno) {
        studentRepository.deleteStudentByRollno(rollno);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/student/save")
    public ResponseEntity<Void> saveOrUpdateStudent(@RequestBody Student student) {
        Student student1 =studentRepository.findByRollno(student.getRollno());
        student1.setName(student.getName());
        studentRepository.save(student1);
        studentRepository.updateStudentDetails(student.getRollno(), student.getName(), student.getAddress(), student.getPhone());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }

    @PostMapping("/register/user")
    public ResponseEntity<Void> registerUser(@RequestBody User user) {
        customUserDetailsService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
