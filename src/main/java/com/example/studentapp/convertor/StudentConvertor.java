package com.example.studentapp.convertor;

import com.example.studentapp.datamodel.Student;
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
        studentEntity.setCoursesList(studentDto.getCoursesList());
        return studentEntity;
    }

    public StudentDto entityToDtoConvertor(@NonNull Student student) {
        return new StudentDto(
                student.getRollno(), student.getName(),
                student.getAddress(), student.getPhone(),
                student.getCoursesList()
        );
    }

    public List<StudentDto> entityToDtoListConvertor(@NonNull List<Student> studentList) {
        return studentList.stream().map(this::entityToDtoConvertor).collect(Collectors.toList());
    }
}
