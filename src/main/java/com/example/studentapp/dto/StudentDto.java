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

    @NonNull String name;

    @NonNull String address;

    @NonNull String phone;

    @JsonIgnoreProperties(value = "studentList", allowSetters = true)
    Set<CourseDto> coursesList;
}
