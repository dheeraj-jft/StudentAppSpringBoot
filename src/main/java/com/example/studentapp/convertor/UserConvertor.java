package com.example.studentapp.convertor;


import com.example.studentapp.datamodel.User;
import com.example.studentapp.dto.UserDto;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConvertor {
    public User dtoToEntityConvertor(@NonNull UserDto userDto) {
        User userEntity= new User();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setRole(userDto.getRole());
        return userEntity;
    }

    public UserDto entityToDtoConvertor(@NonNull User user) {
        return new UserDto(user.getUsername(),user.getPassword(), user.getRole());
    }

    public List<UserDto> entityToDtoListConvertor(@NonNull List<User> userList) {
        return userList.stream().map(this::entityToDtoConvertor).collect(Collectors.toList());
    }
}
