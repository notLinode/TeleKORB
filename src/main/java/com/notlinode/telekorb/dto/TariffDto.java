package com.notlinode.telekorb.dto;

import com.notlinode.telekorb.model.BandwidthCost;
import com.notlinode.telekorb.model.Unlimited;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TariffDto {

    private Long id;

    private String name;

    private int internetBandwidth;
    private int phoneBandwidth;

    private BandwidthCost costs;
    private double baseCost;
    private int totalCost;

    private List<Unlimited> unlimiteds;

    private boolean isPersonal;

}
