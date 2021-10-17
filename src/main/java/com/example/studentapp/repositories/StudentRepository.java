package com.example.studentapp.repositories;

import com.example.studentapp.datamodel.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Modifying
    @Transactional
    @Query(value="update Student s set s.name=:name , s.address=:address, s.phone=:phone where s.rollno=:rollno")
     void updateStudentDetails( @Param("rollno") Integer rollno, @Param("name") String name, @Param("address") String address, @Param("phone") String phone );

    @Modifying
    @Transactional
    @Query(value="delete Student s where s.rollno=:rollno")
    void deleteStudentByRollno(@Param("rollno")Integer rollno);


}
