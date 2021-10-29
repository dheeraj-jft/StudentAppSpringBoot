package com.example.studentapp.repositories;

import com.example.studentapp.datamodel.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {

    Student findByRollno(String rollno);

}
