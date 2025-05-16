package com.domus.domus.mappers;

import com.domus.domus.dto.UserDto;
import com.domus.domus.entities.User;

public class UserMapper {
    public UserDto toDto (User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());

        return dto;
    }

    public User toEntity (UserDto dto){
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());

        return user;
    }

}
