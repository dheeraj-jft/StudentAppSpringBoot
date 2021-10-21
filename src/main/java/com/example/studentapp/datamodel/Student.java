package com.example.studentapp.datamodel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
public class Student {

    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Id
    @Column(nullable = false, unique = true)
    private int rollno;

    private String name;
    private String address;
    private String phone;
    @ManyToMany
    private Set<Course> coursesList;

}
