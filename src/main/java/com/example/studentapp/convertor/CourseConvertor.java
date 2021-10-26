package com.example.studentapp.convertor;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.datamodel.Student;
import com.example.studentapp.dto.CourseDto;
import com.example.studentapp.dto.StudentDto;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseConvertor {

    public Course dtoToEntityConvertor(@NonNull CourseDto courseDto) {
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
        return courseEntity;
    }

    public CourseDto entityToDtoConvertor(@NonNull Course course) {
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
    }

    public List<CourseDto> entityToDtoListConvertor(@NonNull List<Course> courseList) {
        return courseList.stream().map(this::entityToDtoConvertor).collect(Collectors.toList());
    }
}
