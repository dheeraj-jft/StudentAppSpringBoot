package com.example.studentapp.controller;

import com.example.studentapp.datamodel.Student;
import com.example.studentapp.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

     @GetMapping("/login")
     public String login(){
         return "login";
     }
     @GetMapping("/student/dashboard")
        public String dashboard(){
        return "dashboard";
    }
    @GetMapping("/")
    public String home(){
        return "dashboard";
    }

    @PostMapping("/student/add")
    public ResponseEntity<Void> addStudent(@RequestBody Student student){
        studentRepository.save(student);
        System.out.println("Saved to database");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/student/studentlist")
    public ResponseEntity<List<Student>> getStudentList() {
        return new ResponseEntity<List<Student>>(studentRepository.findAll(), HttpStatus.OK);
    }
    @DeleteMapping("/student/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PutMapping("/student/save")
    public ResponseEntity<Void> saveOrUpdateStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
