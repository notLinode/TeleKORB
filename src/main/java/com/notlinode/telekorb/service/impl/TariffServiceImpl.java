package com.notlinode.telekorb.service.impl;

import com.notlinode.telekorb.dto.TariffDto;
import com.notlinode.telekorb.model.Tariff;
import com.notlinode.telekorb.repository.TariffRepository;
import com.notlinode.telekorb.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TariffServiceImpl implements TariffService {

    @Autowired
    private TariffRepository tariffRepo;

    @Override
    public Optional<TariffDto> getFeatured(int featuredPos) {
        var tariff = tariffRepo.getFirstByFeaturedPosEquals(featuredPos);
        return tariff.map(this::mapEntityToDto);
    }

    @Override
    public List<TariffDto> getAllPremade() {
        var tariffs = tariffRepo.getAllByIsPersonalIsFalse();
        List<TariffDto> tariffDtos = new ArrayList<>();
        tariffs.forEach(tariff -> tariffDtos.add(mapEntityToDto(tariff)));
        return tariffDtos;
    }

    @Override
    public Tariff getNewPersonal(Tariff personalTemplate) {
        return Tariff.builder()
                .name(personalTemplate.getName())
                .internetBandwidth(personalTemplate.getInternetBandwidth())
                .phoneBandwidth(personalTemplate.getPhoneBandwidth())
                .costs(personalTemplate.getCosts())
                .baseCost(personalTemplate.getBaseCost())
                .unlimiteds(List.copyOf(personalTemplate.getUnlimiteds()))
                .isPersonal(personalTemplate.isPersonal())
                .build();
    }

    @Override
    public void updateTariff(TariffDto tariffDto) throws Exception {
        var tariffOptional = tariffRepo.findById(tariffDto.getId());
        if (tariffOptional.isEmpty()) {
            throw new Exception("The given tariff DTO either doesn't have an ID, or the ID " + tariffDto.getId() + " is not present");
        }

        var tariff = tariffOptional.get();

        if (tariffDto.getName() != null) {
            tariff.setName(tariffDto.getName());
        }

        tariff.setInternetBandwidth(tariffDto.getInternetBandwidth());
        tariff.setPhoneBandwidth(tariffDto.getPhoneBandwidth());
        tariff.setUnlimiteds(tariffDto.getUnlimiteds());

        System.out.println(tariff);
        tariffRepo.save(tariff);
    }

    @Override
    public TariffDto mapEntityToDto(Tariff entity) {
        return TariffDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .internetBandwidth(entity.getInternetBandwidth())
                .phoneBandwidth(entity.getPhoneBandwidth())
                .costs(entity.getCosts())
                .baseCost(entity.getBaseCost())
                .totalCost((int)entity.getTotalCost())
                .unlimiteds(entity.getUnlimiteds())
                .isPersonal(entity.isPersonal())
                .build();
    }

}
