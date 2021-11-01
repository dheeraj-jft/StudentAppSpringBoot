package com.example.studentapp.datamodel;

import com.example.studentapp.util.UuidGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    private String id = UuidGenerator.uuid();

    @Column(nullable = false, unique = true)
    private String rollno;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @JsonIgnoreProperties(value = "studentList", allowSetters = true)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Course> coursesList;

}
