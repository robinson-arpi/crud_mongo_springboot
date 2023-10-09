package com.crudmongoback.security.entity;

import com.crudmongoback.security.enums.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Document(collection = "users")
public class UserEntity {
    @Id
    private int id;
    private String username;
    private String email;
    private String password;
    private List<RoleEnum> roles;

    public UserEntity() {}
}
