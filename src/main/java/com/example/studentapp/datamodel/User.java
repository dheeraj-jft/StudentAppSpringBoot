package com.example.studentapp.datamodel;

import com.example.studentapp.enums.Provider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(unique = true)
    private String emailAddress;

    @Column(nullable = false)
    private boolean isOAuthEnabled = false;

    @Enumerated(EnumType.STRING)
    private Provider provider;

}
