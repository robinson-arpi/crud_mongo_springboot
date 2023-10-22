package com.module_security.service;

import com.module_security.entity.UserEntity;
import com.module_security.enums.RoleEnum;
import com.crudmongoback.global.exceptions.AttributeException;
import com.crudmongoback.global.utils.Operations;
import com.module_security.dto.CreateUserDto;
import com.module_security.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEntityService {
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    private UserEntity mapUserFromDto(CreateUserDto dto){
        int id = Operations.autoIncrement(userEntityRepository.findAll());
        // Decode password of frontend
        String password = passwordEncoder.encode(dto.getPassword());
        List<RoleEnum> roles =
                dto.getRoles().stream().map(rol -> RoleEnum.valueOf(rol)).collect(Collectors.toList());
        return new UserEntity(id, dto.getUsername(), dto.getEmail(), password, roles);
    }
}