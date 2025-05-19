package com.notlinode.telekorb.service;

import com.notlinode.telekorb.dto.UserDto;
import com.notlinode.telekorb.model.UserEntity;

import java.util.Optional;

public interface UserService {

    UserEntity createUser(UserDto userDto);

    Optional<UserEntity> findUserByUsername(String username);

    void changeTariff(String username, Long tariffId) throws Exception;

    void removeTariff(String username) throws Exception;

    UserDto mapEntityToDto(UserEntity entity);

}
