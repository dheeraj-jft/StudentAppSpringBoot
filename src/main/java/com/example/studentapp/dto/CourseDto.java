package com.example.studentapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CourseDto {

    private String courseId;

    private String courseName;

    @JsonIgnoreProperties(value = "coursesList", allowSetters = true)
    private Set<StudentDto> studentList;

    private TeacherDto teacherDto;
}
