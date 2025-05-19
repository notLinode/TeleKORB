package com.notlinode.telekorb.service;

import com.notlinode.telekorb.dto.TariffDto;
import com.notlinode.telekorb.model.Tariff;

import java.util.List;
import java.util.Optional;

public interface TariffService {

    Optional<TariffDto> getFeatured(int featuredPos);

    List<TariffDto> getAllPremade();

    Tariff getNewPersonal(Tariff personalTemplate);

    void updateTariff(TariffDto tariff) throws Exception;

    TariffDto mapEntityToDto(Tariff entity);

}
