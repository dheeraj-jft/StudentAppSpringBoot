package com.example.studentapp.controller;

import com.example.studentapp.datamodel.Student;
import com.example.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/studentlist")
    public ResponseEntity<List<Student>> getStudentList() {
        return new ResponseEntity<>(studentService.getStudentsList(), HttpStatus.OK);
    }

    @GetMapping("/studentDetails/{rollno}")
    public String getStudentDetails(@PathVariable("rollno") Integer rollno, Model model, Authentication authentication) {
        Student student= studentService.findStudentByRollno(rollno);
        model.addAttribute("student",student);
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "studentDetails";
    }

    @GetMapping("/student/{rollno}")
    public ResponseEntity<Student> getStudent(@PathVariable("rollno") Integer rollno) {
        Student student= studentService.findStudentByRollno(rollno);
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PostMapping("/student/save")
    public ResponseEntity<Void> addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/student/save")
    public ResponseEntity<Void> updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/student/delete/{rollno}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer rollno) {
        studentService.deleteStudent(rollno);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }




}
