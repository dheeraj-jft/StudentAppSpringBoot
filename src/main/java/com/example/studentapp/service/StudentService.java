package com.example.studentapp.service;

import com.example.studentapp.datamodel.Student;
import com.example.studentapp.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<Student> getStudentsList();

    void addStudent(StudentDto studentDto);

    void deleteStudent(String rollno);

    void updateStudent(StudentDto studentDto);

    Student findStudentByRollno(String rollno);

    Boolean isStudentExists(String rollno);
}
