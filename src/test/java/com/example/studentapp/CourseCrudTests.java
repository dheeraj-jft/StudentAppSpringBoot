package com.example.studentapp;

import com.example.studentapp.datamodel.Course;
import com.example.studentapp.dto.CourseDto;
import com.example.studentapp.service.CourseService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CourseCrudTests {
    final String COURSEID = "RCAI551";

    @Autowired
    CourseService courseService;

    @Test
    @Order(1)
    void testCreateCourse() {

        CourseDto course1 = new CourseDto();
        course1.setCourseName("MySQL");
        course1.setCourseId(COURSEID);
        courseService.addCourse(course1);
        assertNotNull(courseService.findByCourseId(COURSEID));
    }

    @Test
    @Order(2)
    void testReadAllCourses() {
        List<Course> courseList = courseService.getCourseList();
        Assertions.assertTrue(courseList.size() > 1);
    }

    @Test
    @Order(3)
    void checkCourseName() {
        Course course = courseService.findByCourseId(COURSEID);
        assert course != null;
        assertEquals("MySQL", course.getCourseName());
    }

    @Test
    @Order(4)
    void checkupdateCourseName() {
        CourseDto courseDto = new CourseDto();
        courseDto.setCourseId(COURSEID);
        courseDto.setCourseName("MySql");
        courseService.updateCourse(courseDto);
        assertEquals("MySql", courseService.findByCourseId(COURSEID).getCourseName());

    }

    @Test
    @Order(5)
    void checkDeleteCourse() {
        courseService.deleteCourse(COURSEID);
        Assertions.assertFalse(courseService.isExists(COURSEID));
    }


}
