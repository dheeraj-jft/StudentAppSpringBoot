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
public class StudentConvertor {

    public Student dtoToEntityConvertor(@NonNull StudentDto studentDto) {
        Student studentEntity = new Student();
        studentEntity.setRollno(studentDto.getRollno());
        studentEntity.setName(studentDto.getName());
        studentEntity.setAddress(studentDto.getAddress());
        studentEntity.setPhone(studentDto.getPhone());
        studentEntity.setCoursesList(studentDto.getCoursesList().stream()
                .map(courseDto ->
                {
                    Course course = new Course();
                    course.setCourseId(courseDto.getCourseId());
                    return course;
                })
                .collect(Collectors.toSet()));
        return studentEntity;
    }

    public StudentDto entityToDtoConvertor(@NonNull Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setRollno(student.getRollno());
        studentDto.setName(student.getName());
        studentDto.setAddress(student.getAddress());
        studentDto.setPhone(student.getPhone());
        studentDto.setCoursesList(student.getCoursesList().stream()
                .map(course -> {
                    CourseDto courseDto = new CourseDto();
                    courseDto.setCourseId(course.getCourseId());
                    courseDto.setCourseName(course.getCourseName());
                    return courseDto;
                })
                .collect(Collectors.toSet())
        );
        return studentDto;
    }

    public List<StudentDto> entityToDtoListConvertor(@NonNull List<Student> studentList) {
        return studentList.stream().map(this::entityToDtoConvertor).collect(Collectors.toList());
    }
}
