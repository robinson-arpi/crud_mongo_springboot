package com.crudmongoback.security.service;

import com.crudmongoback.global.exceptions.AttributeException;
import com.crudmongoback.security.dto.CreateUserDto;
import com.crudmongoback.security.entity.UserEntity;
import com.crudmongoback.security.enums.RoleEnum;
import com.crudmongoback.security.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityService {
    @Autowired
    private UserEntityRepository userEntityRepository;

    public UserEntity create(CreateUserDto dto) throws AttributeException{
        if(userEntityRepository.existsByEmail(dto.getEmail()))
            throw new AttributeException("email already in use");
        if(userEntityRepository.existsByUsername(dto.getUsername()))
            throw new AttributeException("username already in use");
        int id = autoIncrement();
        List<RoleEnum> roles =
                dto.getRoles().stream().map(rol -> RoleEnum.valueOf(rol)).collect(Collectors.toList());
        UserEntity userEntity = new UserEntity(id,dto.getUsername(), dto.getEmail(), dto.getPassword(), roles);
        return userEntityRepository.save(userEntity);
    }

    private int autoIncrement(){
        List<UserEntity> users = userEntityRepository.findAll();
        return users.isEmpty()? 1 :
                users.stream().max(Comparator.comparing(UserEntity::getId)).get().getId() + 1;

    }
}