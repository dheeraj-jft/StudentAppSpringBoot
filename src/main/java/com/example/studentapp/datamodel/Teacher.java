package com.example.studentapp.datamodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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

    @OneToMany
    private Set<Course> courseSet;


}
