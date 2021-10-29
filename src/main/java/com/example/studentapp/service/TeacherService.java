package com.example.studentapp.service;

import com.example.studentapp.datamodel.Teacher;
import com.example.studentapp.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    void addTeacher(TeacherDto teacherDto);

    void updateTeacher(TeacherDto TeacherDto);

    List<Teacher> getTeacherList();

    void deleteTeacher(String teacherId);

}
