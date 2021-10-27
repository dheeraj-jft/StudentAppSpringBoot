package com.example.studentapp.service.impl;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.datamodel.Student;
import com.example.studentapp.dto.CourseDto;
import com.example.studentapp.dto.StudentDto;
import com.example.studentapp.repositories.CourseRepository;
import com.example.studentapp.repositories.StudentRepository;
import com.example.studentapp.service.StudentService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<StudentDto> getStudentsList() {
        return studentRepository.findAll().stream().map(student -> {
            StudentDto studentDto=new StudentDto();
            studentDto.setName(student.getName());
            studentDto.setPhone(student.getPhone());
            studentDto.setAddress(student.getAddress());
            studentDto.setRollno(student.getRollno());
            studentDto.setCoursesList(student.getCoursesList().stream().map(course -> {
                CourseDto courseDto = new CourseDto();
                courseDto.setCourseId(course.getCourseId());
                courseDto.setCourseName(course.getCourseName());
                return courseDto;
            }).collect(Collectors.toSet()));
            return studentDto;
        }).collect(Collectors.toList());
    }

    @Override
    public void addStudent(StudentDto studentDto) {
        Student student=new Student();
        student.setRollno(studentDto.getRollno());
        student.setPhone(studentDto.getPhone());
        student.setAddress(studentDto.getAddress());
        student.setName(studentDto.getName());
        student.setCoursesList(studentDto.getCoursesList().stream().map(courseDto ->
        {
            Course course=new Course();
            course.setCourseId(courseDto.getCourseId());
            return course;
        }).collect(Collectors.toSet()));

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
    public void updateStudent(StudentDto studentDto) {
        Student student = studentRepository.findByRollno(studentDto.getRollno());
        student.setName(studentDto.getName());
        student.setAddress(studentDto.getAddress());
        student.setPhone(studentDto.getPhone());
        student.setCoursesList(studentDto.getCoursesList().stream().map(courseDto -> {
            Course course=new Course();
            course.setCourseId(courseDto.getCourseId());
            return course;
        }).collect(Collectors.toSet()));
        studentRepository.save(student);
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
