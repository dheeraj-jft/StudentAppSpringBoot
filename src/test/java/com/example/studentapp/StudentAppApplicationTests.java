package com.example.studentapp;

import com.example.studentapp.controller.ProfileController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class StudentAppApplicationTests {

	@Autowired
	ProfileController controller;
	@Test
	void contextLoads() {
		assertNotNull(controller);
	}


}
