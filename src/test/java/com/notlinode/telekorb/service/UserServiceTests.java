package com.notlinode.telekorb.service;

import com.notlinode.telekorb.dto.UserDto;
import com.notlinode.telekorb.model.UserEntity;
import com.notlinode.telekorb.repository.UserRepository;
import com.notlinode.telekorb.service.impl.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository repo;
    @Mock
    private PhoneNumberService phoneService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void UserService_CreateUser_ReturnCorrectEntity() {
        var date = new Date();

        UserDto userDto = UserDto.builder()
                .createdAt(date)
                .phoneNum("79001111111")
                .build();
        UserEntity userEntity = UserEntity.builder()
                .createdAt(date)
                .username("79001111111")
                .password("{noop}")
                .build();

        var createdUser = userService.createUser(userDto);

        Assertions.assertThat(createdUser).isEqualTo(userEntity);
    }

    @Test
    public void UserService_RemoveTariff_ThrowExceptionWhenBadUsername() {
        Mockito.when(userService.findUserByUsername(Mockito.any(String.class))).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.removeTariff("123"))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("123");
    }

}
