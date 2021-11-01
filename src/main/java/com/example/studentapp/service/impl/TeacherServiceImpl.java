package com.example.studentapp.service.impl;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.datamodel.Teacher;
import com.example.studentapp.dto.TeacherDto;
import com.example.studentapp.exception.DuplicateTeacherException;
import com.example.studentapp.repositories.CourseRepository;
import com.example.studentapp.repositories.TeacherRepository;
import com.example.studentapp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public void addTeacher(TeacherDto teacherDto) {
        Teacher teacher = teacherRepository.findByTeacherId(teacherDto.getTeacherId());
        if (teacher != null) {
            throw new DuplicateTeacherException(teacher.getTeacherId() + " is already present, use different id");
        } else {
            teacher = new Teacher();
            teacher.setTeacherId(teacherDto.getTeacherId());
            teacher.setFirstName(teacherDto.getFirstName());
            teacher.setLastName(teacherDto.getLastName());
            teacher.setAddress(teacherDto.getAddress());
            teacher.setPhone(teacherDto.getPhone());
            Teacher finalTeacher = teacher;
            teacher.setCourseSet(teacherDto.getCourseSet().stream().map(
                    courseDto ->
                    {
                        Course course = courseRepository.findByCourseId(courseDto.getCourseId());
                        course.setTeacher(finalTeacher);
                        return course;
                    }
            ).collect(Collectors.toSet()));
            teacherRepository.save(teacher);
        }


    }

    @Override
    public void updateTeacher(TeacherDto teacherDto) {
        Teacher teacher = teacherRepository.findByTeacherId(teacherDto.getTeacherId());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setPhone(teacherDto.getPhone());
        teacher.setAddress(teacherDto.getAddress());
        teacher.getCourseSet().stream().map(course -> {
            course.setTeacher(null);
            return course;
        }).collect(Collectors.toList());
        teacher.setCourseSet(teacherDto.getCourseSet().stream().map(courseDto -> {
                    Course course = courseRepository.findByCourseId(courseDto.getCourseId());
                    course.setTeacher(teacher);
                    return course;
                }
        ).collect(Collectors.toSet()));
        teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getTeacherList() {
        return teacherRepository.findAll();
    }

    @Override
    public void deleteTeacher(String teacherId) {
        Teacher teacher = teacherRepository.findByTeacherId(teacherId);
        teacher.getCourseSet().stream().map(course -> {
            Course course1 = courseRepository.findByCourseId(course.getCourseId());
            course1.setTeacher(null);
            courseRepository.save(course);
            return course;
        }).collect(Collectors.toList());
        teacher.setCourseSet(null);
        teacherRepository.delete(teacher);
    }

}
