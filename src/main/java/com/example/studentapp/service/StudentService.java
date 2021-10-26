package com.example.studentapp.service;

import com.example.studentapp.datamodel.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudentsList();

    void addStudent(Student student);

    void deleteStudent(Integer rollno);

    void updateStudent(Student student);

    Student findStudentByRollno(Integer rollno);

    Boolean isStudentExists(Integer rollno);
}
