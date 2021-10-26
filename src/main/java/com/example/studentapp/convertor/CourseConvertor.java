package com.example.studentapp.convertor;

import com.example.studentapp.datamodel.Student;
import com.example.studentapp.dto.StudentDto;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StudentConvertor {
    public Student dtoToEntityConvertor( @NonNull StudentDto studentDto){
        Student studentEntity= new Student();
        studentEntity.setRollno(studentDto.getRollno());
        studentEntity.setName(studentDto.getName());
        studentEntity.setAddress(studentDto.getAddress());
        studentEntity.setPhone(studentDto.getPhone());
        studentEntity.setCoursesList(studentDto.getCoursesList());
        return studentEntity;
    }
    public StudentDto EntityToDtoConvertor(@NonNull Student student){
        return new StudentDto(
                student.getRollno(), student.getName(),
                student.getAddress(), student.getPhone(),
                student.getCoursesList()
        );
    }
}
