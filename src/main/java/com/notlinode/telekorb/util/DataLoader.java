package com.notlinode.telekorb.util;

import com.notlinode.telekorb.model.BandwidthCost;
import com.notlinode.telekorb.model.Tariff;
import com.notlinode.telekorb.model.Unlimited;
import com.notlinode.telekorb.repository.BandwidthCostRepository;
import com.notlinode.telekorb.repository.TariffRepository;
import com.notlinode.telekorb.repository.UnlimitedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private final TariffRepository tariffRepo;
    private final BandwidthCostRepository costsRepo;
    private final UnlimitedRepository unlimitedRepo;

    @Autowired
    public DataLoader(
            TariffRepository tariffRepo,
            BandwidthCostRepository costsRepo,
            UnlimitedRepository unlimitedRepo
    ) {
        this.tariffRepo = tariffRepo;
        this.costsRepo = costsRepo;
        this.unlimitedRepo = unlimitedRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var generalCosts = BandwidthCost.builder()
                .perGigabyte(1.5)
                .perMinute0To200(1.5)
                .perMinuteAbove200(0.25)
                .build();
        costsRepo.save(generalCosts);

        var personalTariff = Tariff.builder()
                .name("Собственный тариф")
                .internetBandwidth(0)
                .phoneBandwidth(0)
                .featuredPos(0)
                .costs(generalCosts)
                .baseCost(209.0)
                .unlimiteds(List.of())
                .isPersonal(true)
                .build();
        tariffRepo.save(personalTariff);

        var unlimitedVk = Unlimited.builder()
                .name("ВКонтакте")
                .imgPath("vk-logo.svg")
                .cost(50.0)
                .build();
        var unlimitedYt = Unlimited.builder()
                .name("YouTube")
                .imgPath("yt-logo.svg")
                .cost(80.0)
                .build();
        var unlimitedTg = Unlimited.builder()
                .name("Telegram")
                .imgPath("tg-logo.svg")
                .cost(50.0)
                .build();
        var unlimitedProverka = Unlimited.builder()
                .name("Проверка")
                .imgPath("proverka-logo.png")
                .cost(20.0)
                .build();
        var unlimitedTt = Unlimited.builder()
                .name("TikTok")
                .imgPath("tiktok-logo.svg")
                .cost(50.0)
                .build();
        var unlimitedWhatsapp = Unlimited.builder()
                .name("Whatsapp")
                .imgPath("whatsapp-logo.svg")
                .cost(50.0)
                .build();
        var unlimitedsList = List.of(unlimitedVk, unlimitedTt, unlimitedProverka, unlimitedWhatsapp, unlimitedYt, unlimitedTg);
        unlimitedRepo.saveAll(unlimitedsList);

        var bulborbTariff = Tariff.builder()
                .name("Бульборб Про")
                .internetBandwidth(45)
                .phoneBandwidth(800)
                .featuredPos(1)
                .costs(generalCosts)
                .baseCost(9.5)
                .unlimiteds(List.of(unlimitedVk, unlimitedYt, unlimitedTg, unlimitedProverka, unlimitedTt))
                .build();
        tariffRepo.save(bulborbTariff);

        var tariffmin = Tariff.builder()
                .name("Тарифмин")
                .internetBandwidth(12)
                .phoneBandwidth(150)
                .featuredPos(2)
                .costs(generalCosts)
                .baseCost(6.0)
                .unlimiteds(List.of(unlimitedVk, unlimitedTg, unlimitedWhatsapp))
                .build();
        tariffRepo.save(tariffmin);
    }

}
