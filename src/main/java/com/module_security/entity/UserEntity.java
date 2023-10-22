package com.module_security.entity;

import com.module_security.enums.RoleEnum;
import com.crudmongoback.global.entity.EntityId;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import lombok.Getter;

@Getter
@Document(collection = "users")
public class UserEntity extends EntityId {
    private String username;
    private String email;
    private String password;
    private List<RoleEnum> roles;

    public UserEntity(int id, String username, String email, String password, List<RoleEnum> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    @Override
    public int getId() {
        return super.getId();
    }
    @Override
    public void setId(int id) {
        super.setId(id);
    }
}
