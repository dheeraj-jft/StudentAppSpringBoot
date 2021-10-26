package com.example.studentapp;

import com.example.studentapp.controller.ProfileController;
import com.example.studentapp.datamodel.Course;
import com.example.studentapp.service.CourseService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentCRUDTests {
	 final String COURSEID="RCAI551";
	@Autowired
	ProfileController controller;
	@Test
	@Order(1)
	void contextLoads() {
		assertNotNull(controller);
	}
	@Autowired
	CourseService courseService;
	@Test
	@Order(2)
	void testCreateCourse(){

		Course course1 = new Course();
		course1.setCourseName("MySQL");
		course1.setCourseId(COURSEID);
		courseService.addCourse(course1);
		assertNotNull(courseService.findByCourseId(COURSEID));
	}
	@Test
	@Order(3)
	void testReadAllCourses(){
		List<Course> courseList=courseService.getCourseList();
		Assertions.assertTrue(courseList.size()>1);
	}
	@Test
	@Order(4)
	void checkCourseName(){
		Course course = courseService.findByCourseId(COURSEID);
		assert course !=null;
		assertEquals("MySQL",course.getCourseName());
	}
	@Test
	@Order(5)
	void checkupdateCourseName(){
		Course course =courseService.findByCourseId(COURSEID);
		assert course!=null;
		course.setCourseName("MySql");
		courseService.updateCourse(course);
		assertEquals("MySql",courseService.findByCourseId(COURSEID).getCourseName());

	}
	@Test
	@Order(6)
	void checkDeleteCourse(){
		courseService.deleteCourse(COURSEID);
		Assertions.assertFalse(courseService.isExists(COURSEID));
	}




}
