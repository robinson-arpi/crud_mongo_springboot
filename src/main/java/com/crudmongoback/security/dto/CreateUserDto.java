package com.crudmongoback.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@AllArgsConstructor
public class CreateUserDto {
    @NotBlank(message = "username is mandatory")
    private String username;
    @Email(message = "invalid email")
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotEmpty(message = "roles are mandatory")
    List<String> roles;
}
