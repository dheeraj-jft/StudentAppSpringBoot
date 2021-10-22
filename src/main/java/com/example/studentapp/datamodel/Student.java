package com.example.studentapp.datamodel;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @JsonIgnoreProperties(value = "studentList" , allowSetters = true)
    @ManyToMany
    private Set<Course> coursesList;

}
