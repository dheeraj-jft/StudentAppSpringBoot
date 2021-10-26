package com.example.studentapp.service.impl;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.datamodel.Student;
import com.example.studentapp.repositories.CourseRepository;
import com.example.studentapp.repositories.StudentRepository;
import com.example.studentapp.service.StudentService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Student> getStudentsList() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(String rollno) {
        Student student = studentRepository.findByRollno(rollno);
        student.getCoursesList().forEach(course -> {
            Course course1 = courseRepository.findByCourseId(course.getCourseId());
            val studentSet = course1.getStudentList();
            studentSet.remove(student);
            course1.setStudentList(studentSet);
            courseRepository.save(course1);
        });
        studentRepository.deleteById(rollno);
    }

    @Override
    public void updateStudent(Student student) {
        Student student1 = studentRepository.findByRollno(student.getRollno());
        student1.setName(student.getName());
        student1.setAddress(student.getAddress());
        student1.setPhone(student.getPhone());
        student1.setCoursesList(student.getCoursesList());
        studentRepository.save(student1);
    }

    @Override
    public Student findStudentByRollno(String rollno) {
        return studentRepository.findByRollno(rollno);
    }

    @Override
    public Boolean isStudentExists(String rollno) {
        return studentRepository.existsById(rollno);
    }
}
