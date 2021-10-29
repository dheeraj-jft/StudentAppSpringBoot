package com.example.studentapp.controller;

import com.example.studentapp.datamodel.Student;
import com.example.studentapp.dto.CourseDto;
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
import java.util.stream.Collectors;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/list")
    public ResponseEntity<List<Student>> getStudentList() {
        return new ResponseEntity<>(studentService.getStudentsList(), HttpStatus.OK);
    }

    @GetMapping("/details/{rollno}")
    public String getStudentDetails(@PathVariable("rollno") String rollno, Model model, Authentication authentication) {
        Student student = studentService.findStudentByRollno(rollno);
        StudentDto studentDto = new StudentDto();
        studentDto.setRollno(student.getRollno());
        studentDto.setPhone(student.getPhone());
        studentDto.setAddress(student.getAddress());
        studentDto.setName(student.getName());
        studentDto.setCoursesList(student.getCoursesList().stream().map(course -> {
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseId(course.getCourseId());
            courseDto.setCourseName(course.getCourseName());
            return courseDto;
        }).collect(Collectors.toSet()));
        model.addAttribute("student", studentDto);
        model.addAttribute("role", authentication.getAuthorities().toString());
        return "studentDetails";
    }

    @GetMapping("/{rollno}")
    public ResponseEntity<Student> getStudent(@PathVariable("rollno") String rollno) {
        Student student = studentService.findStudentByRollno(rollno);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto) {
        studentService.addStudent(studentDto);
        return "fragments/successmodal :: successModalFragment(value='Student Added Successfully')";
    }

    @PutMapping
    public String updateStudent(@RequestBody StudentDto studentDto) {
        studentService.updateStudent(studentDto);
        return "fragments/successmodal :: successModalFragment(value='Student Updated Successfully')";
    }

    @DeleteMapping("/{rollno}")
    public String deleteStudent(@PathVariable String rollno) {
        studentService.deleteStudent(rollno);
        return "fragments/successmodal :: successModalFragment(value='Student with Roll No: " + rollno + " is deleted Successfully')";
    }

}
