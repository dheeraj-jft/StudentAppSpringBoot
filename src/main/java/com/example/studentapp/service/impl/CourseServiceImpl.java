package com.example.studentapp.service.impl;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.datamodel.Student;
import com.example.studentapp.dto.CourseDto;
import com.example.studentapp.dto.StudentDto;
import com.example.studentapp.repositories.CourseRepository;
import com.example.studentapp.repositories.StudentRepository;
import com.example.studentapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void addCourse(CourseDto courseDto) {
        Course courseEntity = new Course();
        courseEntity.setCourseName(courseDto.getCourseName());
        courseEntity.setCourseId(courseDto.getCourseId());
        if (courseDto.getStudentList() != null)
            courseEntity.setStudentList(courseDto.getStudentList().stream()
                    .map(studentDto -> {
                        Student student = new Student();
                        student.setRollno(studentDto.getRollno());
                        student.setName(studentDto.getName());
                        student.setPhone(studentDto.getPhone());
                        student.setAddress(studentDto.getAddress());
                        return student;
                    })
                    .collect(Collectors.toSet()));
        courseRepository.save(courseEntity);
    }

    @Override
    public void updateCourse(CourseDto courseDto) {
        Course course = courseRepository.findByCourseId(courseDto.getCourseId());
        course.setCourseName(courseDto.getCourseName());
        courseRepository.save(course);
    }

    @Override
    public List<CourseDto> getCourseList() {

       return  courseRepository.findAll().stream().map(course -> {
           CourseDto courseDto = new CourseDto();
           courseDto.setCourseId(course.getCourseId());
           courseDto.setCourseName(course.getCourseName());
           courseDto.setStudentList(course.getStudentList().stream()
                   .map(student -> {
                       StudentDto studentDto = new StudentDto();
                       studentDto.setRollno(student.getRollno());
                       studentDto.setName(student.getName());
                       studentDto.setAddress(student.getAddress());
                       studentDto.setPhone(student.getPhone());
                       return studentDto;
                   }).collect(Collectors.toSet()));
            return courseDto;
       }).collect(Collectors.toList());
    }

    @Override
    public void deleteCourse(String courseId) {
        Course course = courseRepository.findByCourseId(courseId);
        course.getStudentList().forEach(student -> {
            student.getCoursesList().remove(course);
            studentRepository.save(student);
        });
        courseRepository.delete(course);

    }

    @Override
    public Course findByCourseId(String courseId) {

        return courseRepository.findByCourseId(courseId);
    }

    @Override
    public Boolean isExists(String courseId) {

        return courseRepository.existsById(courseId);
    }
}
