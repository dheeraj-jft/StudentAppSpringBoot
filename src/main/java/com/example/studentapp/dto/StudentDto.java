package com.example.studentapp.dto;

import com.example.studentapp.datamodel.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class StudentDto {

    @NonNull int rollno;

    @NonNull String name;

    @NonNull String address;

    @NonNull String phone;

    @NonNull Set<Course> coursesList;
}
