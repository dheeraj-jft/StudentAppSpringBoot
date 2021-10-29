package com.example.studentapp.repositories;


import com.example.studentapp.datamodel.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, String> {
    Teacher findByTeacherId(String teacherId);
}
