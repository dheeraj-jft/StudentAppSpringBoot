package com.example.studentapp.dto;

import com.example.studentapp.datamodel.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CourseDto {

    private String courseId;

    private String courseName;

    private Set<Student> studentList;
}
