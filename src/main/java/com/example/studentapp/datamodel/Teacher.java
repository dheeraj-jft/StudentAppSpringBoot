package com.example.studentapp.datamodel;

import com.example.studentapp.util.UuidGenerator;
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
public class Teacher {

    @Id
    private String uuid = UuidGenerator.uuid();

    @Column(nullable = false, unique = true)
    private @NonNull String teacherId;

    @Column(nullable = false)
    private @NonNull String firstName;

    @Column
    private @NonNull String lastName;

    @Column(nullable = false)
    private @NonNull String phone;

    @Column(nullable = false)
    private @NonNull String address;

    @JsonIgnoreProperties(value = "teacher", allowSetters = true)
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Course> courseSet;


}
