package com.module_security.repository;

import com.module_security.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.Optional;
@Repository
public interface UserEntityRepository extends MongoRepository<UserEntity, Integer> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
