package com.notlinode.telekorb.repository;

import com.notlinode.telekorb.model.PhoneNumber;
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
public class PhoneNumberRepositoryTests {

    @Autowired
    private PhoneNumberRepository repo;

    private PhoneNumber nicePhone1;
    private PhoneNumber nicePhone2;
    private PhoneNumber phone1;

    @BeforeEach
    public void init() {
        nicePhone1 = PhoneNumber.builder()
                .phone("79001111111")
                .isNice(true)
                .build();
        nicePhone2 = PhoneNumber.builder()
                .phone("79002222222")
                .isNice(true)
                .build();
        phone1 = PhoneNumber.builder()
                .phone("79003333333")
                .isNice(false)
                .build();

        repo.saveAll(List.of(nicePhone1, nicePhone2, phone1));
    }

    @Test
    public void PhoneNumberRepository_FindAllByIsNiceIsTrue_ReturnAllNiceNumbers() {
        var nicePhones = List.of(nicePhone1, nicePhone2);

        var foundNicePhones = repo.findAllByIsNiceIsTrue();

        Assertions.assertThatObject(foundNicePhones).isEqualTo(nicePhones);
    }

    @Test
    public void PhoneNumberRepository_FindFirstByIsNiceIsFalse_ReturnNotNicePhoneNumber() {
        var foundPhoneOptional = repo.findFirstByIsNiceIsFalse();

        Assertions.assertThat(foundPhoneOptional).isPresent();
        Assertions.assertThatObject(foundPhoneOptional.get()).isEqualTo(phone1);
    }

}
