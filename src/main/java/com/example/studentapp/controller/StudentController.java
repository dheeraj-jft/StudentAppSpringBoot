package com.example.studentapp.controller;

import com.example.studentapp.repositories.StudentRepository;
import com.example.studentapp.datamodel.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/add")
    public ResponseEntity<Void> addStudent(@RequestBody Student student){
        studentRepository.save(student);
        System.out.println("Saved to database");
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/studentlist")
    public ResponseEntity<List<Student>> getStudentList() {
        return new ResponseEntity<List<Student>>((List<Student>) studentRepository.findAll(), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentRepository.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @PutMapping("/save")
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
