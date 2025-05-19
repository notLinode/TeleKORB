package com.notlinode.telekorb.repository;

import com.notlinode.telekorb.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);

}
