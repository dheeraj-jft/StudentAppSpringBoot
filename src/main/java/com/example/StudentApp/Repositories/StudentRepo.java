package com.example.StudentApp.Repositories;

import com.example.StudentApp.datamodel.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepo extends CrudRepository<Student, Integer> {

}
