package com.example.studentapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudentDto {

    @NonNull String rollno;

    String name;

    String address;

    String phone;

    @JsonIgnoreProperties(value = "studentList", allowSetters = true)
    Set<CourseDto> coursesList;
}
