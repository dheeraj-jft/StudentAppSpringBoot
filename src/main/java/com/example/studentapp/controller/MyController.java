package com.example.studentapp.controller;

import com.example.studentapp.datamodel.Student;
import com.example.studentapp.datamodel.User;
import com.example.studentapp.repositories.StudentRepository;
import com.example.studentapp.repositories.UserRepository;
import com.example.studentapp.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
public class MyController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CustomUserDetailsService customUserDetailsService;

        //for login page
     @GetMapping("/login")
     public String login(){
         return "login";
     }
        //for dashboard page
     @GetMapping("/")
     public String home(){
        return "dashboard";
    }
        //for error page
    @GetMapping("/error")
    public String error(){
        return "error";
    }

//    //for logout page
//    @RequestMapping("/logout/success")
//    public String afterLogut() {
//        return "login";
//    }


    //for register a new user page by admin role
    @GetMapping("/register")
    public String registerUser() {
        return "register";
    }

    //save a new user only ADMIN access
    @PostMapping("/register/user")
    public ResponseEntity<Void> registerUser(@RequestBody User user){
        customUserDetailsService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

       //All authoirity Access mappings

        //for edit own profile
    @GetMapping("/edit/profile")
    public String editProfile(Model model,Authentication principal) {
        model.addAttribute("username", principal.getName());
        System.out.println(principal.getName());
        return "editprofile";
    }


    //updating own details page
    @PutMapping("/edit/profile/update")
    public ResponseEntity<Void> editUserDetails(@RequestBody User user, Principal principal){
        customUserDetailsService.updateUser(user,principal.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }



    // for geting role of current logged in user [ADMIN], [USER]
    @GetMapping("/userrole")
    public ResponseEntity<String> currentUserName(Authentication principal) {
          System.out.println(principal.getAuthorities().toString());
          return new ResponseEntity<>(principal.getAuthorities().toString(),HttpStatus.OK);
    }



    // add new Student to database
    @PostMapping("/student/add")
    public ResponseEntity<Void> addStudent(@RequestBody Student student){
        studentRepository.save(student);
        System.out.println("Saved to database");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // to get list of students
    @GetMapping("/studentlist")
    public ResponseEntity<List<Student>> getStudentList() {
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }


    // to delete user
    @DeleteMapping("/student/delete/{rollno}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer rollno) {
        studentRepository.deleteStudentByRollno(rollno);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    // to update user
    @PutMapping("/student/save")
    public ResponseEntity<Void> saveOrUpdateStudent(@RequestBody Student student) {
         System.out.println(student);
         studentRepository.updateStudentDetails(student.getRollno(),student.getName(),student.getAddress(),student.getPhone());
         return new ResponseEntity<Void>(HttpStatus.OK);
    }


}
