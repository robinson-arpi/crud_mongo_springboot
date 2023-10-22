package com.module_security.controller;

import com.module_security.entity.UserEntity;
import com.module_security.service.UserEntityService;
import com.crudmongoback.global.dto.MessageDto;
import com.crudmongoback.global.exceptions.AttributeException;
import com.module_security.dto.CreateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    UserEntityService userEntityService;


    @PostMapping("/create")
    public ResponseEntity<MessageDto> create(@Valid @RequestBody CreateUserDto dto) throws AttributeException {
        UserEntity userEntity = userEntityService.create(dto);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "user " + userEntity.getUsername() + " have been created"));
    }
}
