package com.example.studentapp.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
public class Course {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Id
    @Column(nullable = false, unique = true)
    private String courseId;

    @Column(nullable = false, unique = true)
    private String courseName;

    @JsonIgnore
    @ManyToMany(mappedBy = "coursesList")
    private Set<Student> studentList;

}
