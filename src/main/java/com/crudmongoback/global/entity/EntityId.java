package com.crudmongoback.global.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public abstract class EntityId {

    @Id
    protected int id;
    public void setId(int id) {
        this.id = id;
    }
}