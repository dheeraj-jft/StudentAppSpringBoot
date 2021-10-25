package com.example.studentapp.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
public class Student {

    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Id
    @Column(nullable = false, unique = true)
    private int rollno;

    private @NonNull String name;
    private @NonNull String address;
    private @NonNull String phone;
    @JsonIgnoreProperties(value = "studentList" , allowSetters = true)
    @ManyToMany
    private Set<Course> coursesList;

}
