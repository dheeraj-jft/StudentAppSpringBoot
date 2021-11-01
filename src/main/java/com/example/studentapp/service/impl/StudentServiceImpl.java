package com.example.studentapp.service.impl;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.datamodel.Student;
import com.example.studentapp.dto.StudentDto;
import com.example.studentapp.exception.RollnoAlreadyExistsException;
import com.example.studentapp.repositories.CourseRepository;
import com.example.studentapp.repositories.StudentRepository;
import com.example.studentapp.service.StudentService;
import lombok.NonNull;
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
    public List<Student> getStudentsList() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudent(StudentDto studentDto) {
        Student student = studentRepository.findByRollno(studentDto.getRollno());
        if (student != null) {
            throw new RollnoAlreadyExistsException("Rollno: " + studentDto.getRollno() + " is already present in records, use different rollno for new student.");
        }
        student = new Student();
        student.setRollno(studentDto.getRollno());
        student.setPhone(studentDto.getPhone());
        student.setAddress(studentDto.getAddress());
        student.setName(studentDto.getName());
        if (studentDto.getCoursesList() != null)
            student.setCoursesList(studentDto.getCoursesList().stream().map(courseDto ->
                    courseRepository.findByCourseId(courseDto.getCourseId())).collect(Collectors.toSet()));
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
        });
        studentRepository.delete(student);
    }

    @Override
    public void updateStudent(@NonNull StudentDto studentDto) {
        Student student = studentRepository.findByRollno(studentDto.getRollno());
        if (studentDto.getName() != null)
            student.setName(studentDto.getName());
        if (studentDto.getAddress() != null)
            student.setAddress(studentDto.getAddress());
        if (studentDto.getPhone() != null)
            student.setPhone(studentDto.getPhone());
        if (studentDto.getCoursesList() != null)
            student.setCoursesList(studentDto.getCoursesList().stream().map(courseDto -> courseRepository.findByCourseId(courseDto.getCourseId())).collect(Collectors.toSet()));
        studentRepository.save(student);
    }

    @Override
    public Student findStudentByRollno(String rollno) {
        return studentRepository.findByRollno(rollno);
    }

    @Override
    public Boolean isStudentExists(String rollno) {
        return studentRepository.existsById(studentRepository.findByRollno(rollno).getId());
    }
}
