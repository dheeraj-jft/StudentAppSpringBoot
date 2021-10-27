package com.example.studentapp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private @NonNull String username;

    private @NonNull String password;

    private @NonNull String role;
}
