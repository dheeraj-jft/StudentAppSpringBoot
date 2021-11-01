package com.example.studentapp.service.impl;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.dto.CourseDto;
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
                    .map(studentDto -> studentRepository.findByRollno(studentDto.getRollno()))
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
    public List<Course> getCourseList() {
        return courseRepository.findAll();
    }

    @Override
    public void deleteCourse(String courseId) {
        Course course = courseRepository.findByCourseId(courseId);
        course.getStudentList().forEach(student -> student.getCoursesList().remove(course));
        course.getTeacher().getCourseSet().remove(course);
        courseRepository.delete(course);
    }

    @Override
    public Course findByCourseId(String courseId) {
        return courseRepository.findByCourseId(courseId);
    }

    @Override
    public Boolean isExists(String courseId) {
        return courseRepository.existsById(courseRepository.findByCourseId(courseId).getId());
    }
}
