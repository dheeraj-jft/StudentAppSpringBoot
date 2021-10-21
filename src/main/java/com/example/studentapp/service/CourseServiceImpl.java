package com.example.studentapp.service;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.datamodel.User;
import com.example.studentapp.repositories.CourseRepository;
import com.example.studentapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;


    @Override
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void updateCourse(Course course, String oldName) {

    }

    @Override
    public List<Course> getCourseList() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourse(String courseId) {

    }
}
