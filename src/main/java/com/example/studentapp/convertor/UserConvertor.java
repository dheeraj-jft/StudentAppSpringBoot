package com.example.studentapp.convertor;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.dto.CourseDto;
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
        courseEntity.setStudentList(courseDto.getStudentList());
        return courseEntity;
    }

    public CourseDto entityToDtoConvertor(@NonNull Course course) {
        return new CourseDto(course.getCourseId(), course.getCourseName()
                , course.getStudentList()
        );
    }

    public List<CourseDto> entityToDtoListConvertor(@NonNull List<Course> courseList) {
        return courseList.stream().map(this::entityToDtoConvertor).collect(Collectors.toList());
    }
}
