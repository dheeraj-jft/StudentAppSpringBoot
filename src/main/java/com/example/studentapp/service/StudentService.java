package com.example.studentapp.service;

import com.example.studentapp.datamodel.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentsList();

    void addStudent(Student student);

    void deleteStudent(String rollno);

    void updateStudent(Student student);

    Student findStudentByRollno(String rollno);

    Boolean isStudentExists(String rollno);
}
