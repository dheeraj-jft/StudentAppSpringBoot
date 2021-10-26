package com.example.studentapp.datamodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Id
    @Column(nullable = false, unique = true)
    private @NonNull String courseId;

    @Column(nullable = false, unique = true)
    private @NonNull String courseName;

    @JsonIgnoreProperties(value = "coursesList", allowSetters = true)
    @ManyToMany(mappedBy = "coursesList", fetch = FetchType.EAGER)
    private Set<Student> studentList;

}
