package com.example.studentapp.service.impl;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.datamodel.Student;
import com.example.studentapp.datamodel.User;
import com.example.studentapp.repositories.CourseRepository;
import com.example.studentapp.repositories.StudentRepository;
import com.example.studentapp.repositories.UserRepository;
import com.example.studentapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;


    @Override
    public void addCourse(Course course) {

        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Course course) {
        Course course1=courseRepository.findByCourseId(course.getCourseId());
        course1.setCourseName(course.getCourseName());
        courseRepository.save(course1);
    }

    @Override
    public List<Course> getCourseList() {

        return courseRepository.findAll();
    }

    @Override
    public void deleteCourse(String courseId) {
        Course course= courseRepository.findByCourseId(courseId);
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
}
