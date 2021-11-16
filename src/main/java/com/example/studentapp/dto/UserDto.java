package com.example.studentapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private @NonNull String username;

    private @NonNull String password;

    private @NonNull String role;

    private String emailAddress;
}
