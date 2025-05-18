package com.notlinode.telekorb.service.impl;

import com.notlinode.telekorb.dto.UserDto;
import com.notlinode.telekorb.model.UserEntity;
import com.notlinode.telekorb.repository.UserRepository;
import com.notlinode.telekorb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserEntity createUser(UserDto userDto) {
        String phoneNum;

        if (Objects.equals(userDto.getPhoneNum(), "0")) {
            Random rng = new Random();
            do {
                phoneNum = String.format("79%09d", rng.nextInt(1_000_000_000));
            } while (this.findUserByUsername(phoneNum).isPresent());
        }
        else {
            phoneNum = userDto.getPhoneNum();
        }

        UserEntity user = UserEntity.builder()
                .createdAt(new Date())
                .username(phoneNum)
                .password("{noop}")
                .name(userDto.getName())
                .city(userDto.getCity())
                .mail(userDto.getMail())
                .build();

        userRepo.save(user);

        return user;
    }

    @Override
    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public UserDto mapEntityToDto(UserEntity entity) {
        return UserDto.builder()
                .createdAt(entity.getCreatedAt())
                .name(entity.getName())
                .city(entity.getCity())
                .mail(entity.getMail())
                .phoneNum(entity.getUsername())
                .build();
    }

}
