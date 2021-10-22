package com.example.studentapp.service;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.datamodel.User;

import java.util.List;

public interface CourseService {
     void addCourse(Course course);
     void updateCourse(Course course);
     List<Course> getCourseList();
     void deleteCourse(String courseId);
     Course findByCourseId(String courseId);
}
