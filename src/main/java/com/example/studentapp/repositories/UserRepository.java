package com.example.studentapp.repositories;

import com.example.studentapp.datamodel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String userName);

    @Modifying
    @Transactional
    @Query(value="update User user set user.username=:username, user.password=:password where user.username=:oldname")
    void updateUserDetails(@Param("username") String username, @Param("password") String password, @Param("oldname") String oldname);
}
