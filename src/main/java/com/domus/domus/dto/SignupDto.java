package com.domus.domus.dto;

import com.domus.domus.entities.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupDto {

    @NotEmpty
    @Email
    @NotBlank
    private String email;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotBlank
    private String username;

    @NotNull(message = "Role is required")
    private Role role;
}

