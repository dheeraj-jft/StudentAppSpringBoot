package com.example.studentapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TeacherDto {

    private String teacherId;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    private Set<CourseDto> courseSet;

}
