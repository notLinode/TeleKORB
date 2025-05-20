package com.notlinode.telekorb.repository;

import com.notlinode.telekorb.model.Tariff;
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
public class TariffRepositoryTests {

    @Autowired
    private TariffRepository repo;

    private Tariff featuredTariff1;
    private Tariff featuredTariff2;
    private Tariff tariff1;

    @BeforeEach
    public void init() {
        featuredTariff1 = Tariff.builder()
                .name("1")
                .featuredPos(1)
                .isPersonal(true)
                .build();
        featuredTariff2 = Tariff.builder()
                .name("2")
                .featuredPos(2)
                .build();
        tariff1 = Tariff.builder()
                .name("3")
                .build();

        repo.saveAll(List.of(featuredTariff1, featuredTariff2, tariff1));
    }

    @Test
    public void TariffRepository_GetFirstByFeaturedPosEquals_ReturnCorrectTariff() {
        var foundTariffOptional = repo.getFirstByFeaturedPosEquals(2);

        Assertions.assertThat(foundTariffOptional).isPresent();
        Assertions.assertThatObject(foundTariffOptional.get()).isEqualTo(featuredTariff2);
    }

    @Test
    public void TariffRepository_GetFirstByFeaturedPosEquals_ReturnEmptyOptional() {
        var foundTariffOptional = repo.getFirstByFeaturedPosEquals(10);

        Assertions.assertThat(foundTariffOptional).isEmpty();
    }

    @Test
    public void TariffRepository_getAllByIsPersonalIsFalse_ReturnAllNonFeatured() {
        var foundTariffIterable = repo.getAllByIsPersonalIsFalse();

        Assertions.assertThat(foundTariffIterable).containsExactly(featuredTariff2, tariff1);
    }

}
