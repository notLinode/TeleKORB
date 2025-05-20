package com.notlinode.telekorb.repository;

import com.notlinode.telekorb.model.Tariff;
import com.notlinode.telekorb.model.UserEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    private UserEntity user1;
    private UserEntity user2;

    @BeforeEach
    public void init() {
        user1 = UserEntity.builder()
                .username("79001111111")
                .password("{noop}")
                .build();
        user2 = UserEntity.builder()
                .username("79002222222")
                .password("{noop}")
                .build();

        repo.saveAll(List.of(user1, user2));
    }

    @Test
    public void UserRepository_FindByUsername_ReturnCorrectUser() {
        var userOptional = repo.findByUsername(user1.getUsername());

        Assertions.assertThat(userOptional).isPresent();
        Assertions.assertThatObject(userOptional.get()).isEqualTo(user1);
    }

}
