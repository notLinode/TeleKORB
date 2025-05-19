package com.notlinode.telekorb.repository;

import com.notlinode.telekorb.model.Tariff;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TariffRepository extends CrudRepository<Tariff, Long> {

    Optional<Tariff> getFirstByFeaturedPosEquals(int featuredPos);

    Iterable<Tariff> getAllByIsPersonalIsFalse();

}
