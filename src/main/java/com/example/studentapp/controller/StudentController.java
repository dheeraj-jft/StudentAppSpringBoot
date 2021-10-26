package com.example.studentapp.controller;

import com.example.studentapp.convertor.StudentConvertor;
import com.example.studentapp.datamodel.Student;
import com.example.studentapp.dto.StudentDto;
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
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentConvertor convertor;

    @GetMapping("/list")
    public ResponseEntity<List<StudentDto>> getStudentList() {
        List<StudentDto> studentDtoList = convertor.entityToDtoListConvertor(studentService.getStudentsList());
        return new ResponseEntity<>(studentDtoList, HttpStatus.OK);
    }

    @GetMapping("/details/{rollno}")
    public String getStudentDetails(@PathVariable("rollno") String rollno, Model model, Authentication authentication) {
        Student student = studentService.findStudentByRollno(rollno);
        StudentDto studentDto = convertor.entityToDtoConvertor(student);
        model.addAttribute("student", studentDto);
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "studentDetails";
    }

    @GetMapping("/{rollno}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable("rollno") String rollno) {
        Student student = studentService.findStudentByRollno(rollno);
        StudentDto studentDto = convertor.entityToDtoConvertor(student);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> addStudent(@RequestBody StudentDto studentDto) {
        Student studentEntity = convertor.dtoToEntityConvertor(studentDto);
        studentService.addStudent(studentEntity);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<Void> updateStudent(@RequestBody StudentDto studentDto) {
        Student studentEntity = convertor.dtoToEntityConvertor(studentDto);
        studentService.updateStudent(studentEntity);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{rollno}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String rollno) {
        studentService.deleteStudent(rollno);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
