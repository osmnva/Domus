package com.domus.domus.dto;

import com.domus.domus.entities.Role;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    //private String email;
    private Role role;
    private String password;
}
