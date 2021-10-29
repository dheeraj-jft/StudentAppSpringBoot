package com.example.studentapp.service;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.dto.CourseDto;

import java.util.List;

public interface CourseService {
    void addCourse(CourseDto course);

    void updateCourse(CourseDto courseDto);

    List<Course> getCourseList();

    void deleteCourse(String courseId);

    Course findByCourseId(String courseId);

    Boolean isExists(String courseId);
}
