package com.notlinode.telekorb.service.impl;

import com.notlinode.telekorb.dto.UserDto;
import com.notlinode.telekorb.model.UserEntity;
import com.notlinode.telekorb.repository.TariffRepository;
import com.notlinode.telekorb.repository.UserRepository;
import com.notlinode.telekorb.service.TariffService;
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

    @Autowired
    private TariffService tariffService;

    @Autowired
    private TariffRepository tariffRepo;

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
        System.out.println(userRepo.findByUsername(username));
        return userRepo.findByUsername(username);
    }

    @Override
    public void changeTariff(String username, Long tariffId) throws Exception {
        var tariffOptional = tariffRepo.findById(tariffId);
        var userOptional = findUserByUsername(username);

        if (tariffOptional.isEmpty()) {
            throw new Exception("Tariff ID " + tariffId + "is not present");
        }
        if (userOptional.isEmpty()) {
            throw new Exception("Username " + username + "is not present");
        }

        var user = userOptional.get();

        var oldTariff = user.getTariff();
        if (oldTariff != null && oldTariff.isPersonal()) {
            user.setTariff(null);
            tariffRepo.delete(oldTariff);
        }

        var newTariff = tariffOptional.get();
        if (newTariff.isPersonal()) {
            var newPersonal = tariffService.getNewPersonal(newTariff);
            tariffRepo.save(newPersonal);
            user.setTariff(newPersonal);
        }
        else {
            user.setTariff(newTariff);
        }

        userRepo.save(user);
    }

    @Override
    public void removeTariff(String username) throws Exception {
        var userOptional = findUserByUsername(username);

        if (userOptional.isEmpty()) {
            throw new Exception("Username " + username + "is not present");
        }

        var user = userOptional.get();
        var tariff = user.getTariff();
        user.setTariff(null);

        if (tariff.isPersonal()) {
            tariffRepo.delete(tariff);
        }

        userRepo.save(user);
    }

    @Override
    public UserDto mapEntityToDto(UserEntity entity) {
        var dto = UserDto.builder()
                .createdAt(entity.getCreatedAt())
                .name(entity.getName())
                .city(entity.getCity())
                .mail(entity.getMail())
                .phoneNum(entity.getUsername())
                .build();

        if (entity.getTariff() != null) {
            dto.setTariff(tariffService.mapEntityToDto(entity.getTariff()));
        }

        return dto;
    }

}
