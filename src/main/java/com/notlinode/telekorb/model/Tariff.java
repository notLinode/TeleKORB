package com.notlinode.telekorb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Tariff {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int internetBandwidth;
    private int phoneBandwidth;

    private int featuredPos;

    @ManyToOne
    private BandwidthCost costs;

    private double baseCost;

    @ManyToMany
    private List<Unlimited> unlimiteds;

    @Builder.Default
    private boolean isPersonal = false;

    public double getTotalCost() {
        double internetCost = internetBandwidth * costs.getPerGigabyte();

        double phoneCost;
        if (phoneBandwidth <= 200) {
            phoneCost = phoneBandwidth * costs.getPerMinute0To200();
        }
        else {
            phoneCost = 200 * costs.getPerMinute0To200() + (phoneBandwidth - 200) * costs.getPerMinuteAbove200();
        }

        double unlimitedsCost = 0;
        for (var unlimited : unlimiteds) {
            unlimitedsCost += unlimited.getCost();
        }

        return baseCost + internetCost + phoneCost + unlimitedsCost;
    }

}
