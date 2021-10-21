package com.example.studentapp.controller;

import com.example.studentapp.datamodel.Student;
import com.example.studentapp.repositories.StudentRepository;
import com.example.studentapp.service.StudentServiceImpl;
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
    StudentRepository studentRepository;

    @Autowired
    StudentServiceImpl studentService;


    @GetMapping("/studentlist")
    public ResponseEntity<List<Student>> getStudentList() {
        return new ResponseEntity<>(studentService.getStudentsList(), HttpStatus.OK);
    }
    @GetMapping("/studentDetails/{rollno}")
    public String getStudentDetails(@PathVariable("rollno") Integer rollno, Model model, Authentication authentication) {
        Student student= studentService.findStudentByRollno(rollno);
        model.addAttribute("student",student);
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "StudentDetails";
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

    @GetMapping("/student/{rollo}")
    public ResponseEntity<Void> deleteStuden(@PathVariable Integer rollno) {
        studentRepository.findByRollno(rollno).getCoursesList().forEach(course -> {
            System.out.println(course.getCourseName());
        });
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



}
