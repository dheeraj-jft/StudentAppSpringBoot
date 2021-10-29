package com.example.studentapp;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.datamodel.Student;
import com.example.studentapp.dto.StudentDto;
import com.example.studentapp.service.StudentService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentCrudTests {

    final String STUDENT_ROLL_NO = "1234567";
    @Autowired
    StudentService studentService;

    @Test
    @Order(1)
    void createStudent() {
        StudentDto student = new StudentDto();
        student.setRollno(STUDENT_ROLL_NO);
        student.setAddress("New Malviya nagar, Jaipur");
        student.setName("Abraim sufi");
        student.setPhone("9100090092");
        studentService.addStudent(student);
        assertNotNull(studentService.findStudentByRollno(STUDENT_ROLL_NO));
    }

    @Test
    @Order(2)
    void updateStudentPhone() {
        String newPhoneno = "9100190010";
        StudentDto studentDto = new StudentDto();
        studentDto.setRollno(STUDENT_ROLL_NO);
        studentDto.setPhone(newPhoneno);
        studentService.updateStudent(studentDto);
        assertEquals(newPhoneno, studentService.findStudentByRollno(STUDENT_ROLL_NO).getPhone());
    }

    @Test
    @Order(3)
    void getCoursesListforStudent() {
        Student student = studentService.findStudentByRollno(STUDENT_ROLL_NO);
        Set<Course> courseList = student.getCoursesList();
        assertEquals(0, courseList.size());
    }

    @Test
    @Order(4)
    void DeleteStudent() {
        studentService.deleteStudent(STUDENT_ROLL_NO);
        assertFalse(studentService.isStudentExists(STUDENT_ROLL_NO));
    }

}
