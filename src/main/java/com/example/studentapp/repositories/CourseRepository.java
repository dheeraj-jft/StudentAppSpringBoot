package com.example.studentapp.repositories;

import com.example.studentapp.datamodel.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
        Course findByCourseId(String courseId);
}
