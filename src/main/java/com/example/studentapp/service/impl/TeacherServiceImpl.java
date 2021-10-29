package com.example.studentapp.service.impl;

import com.example.studentapp.datamodel.Teacher;
import com.example.studentapp.dto.TeacherDto;
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
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherDto.getTeacherId());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setAddress(teacherDto.getAddress());
        teacher.setPhone(teacherDto.getPhone());
        teacher.setCourseSet(teacherDto.getCourseDtos().stream().map(
                courseDto -> courseRepository.findByCourseId(courseDto.getCourseId())
        ).collect(Collectors.toSet()));
        teacherRepository.save(teacher);

    }

    @Override
    public void updateTeacher(TeacherDto teacherDto) {
        Teacher teacher = teacherRepository.findByTeacherId(teacherDto.getTeacherId());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setPhone(teacherDto.getPhone());
        teacher.setAddress(teacherDto.getAddress());
        teacher.setCourseSet(teacherDto.getCourseDtos().stream().map(courseDto ->
                courseRepository.findByCourseId(courseDto.getCourseId())).collect(Collectors.toSet()));
    }

    @Override
    public List<Teacher> getTeacherList() {
        return teacherRepository.findAll();
    }

    @Override
    public void deleteTeacher(String teacherId) {
        teacherRepository.delete(teacherRepository.findByTeacherId(teacherId));
    }

}
