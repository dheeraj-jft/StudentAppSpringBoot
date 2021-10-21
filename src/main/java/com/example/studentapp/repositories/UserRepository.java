package com.example.studentapp.repositories;

import com.example.studentapp.datamodel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String userName);


}
