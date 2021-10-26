package com.example.studentapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private @NonNull String username;

    private @NonNull String password;

    private @NonNull String role;
}
