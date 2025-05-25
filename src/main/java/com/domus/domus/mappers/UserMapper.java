package com.domus.domus.mappers;

import com.domus.domus.dto.UserDto;
import com.domus.domus.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity userDtoToUser(UserDto dto);
    UserDto userToUserDto(UserEntity user);
}