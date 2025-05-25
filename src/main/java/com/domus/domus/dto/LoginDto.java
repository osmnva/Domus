package com.domus.domus.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
