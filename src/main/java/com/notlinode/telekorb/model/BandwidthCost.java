package com.notlinode.telekorb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BandwidthCost {

    @Id
    @GeneratedValue
    private Long id;

    private double perGigabyte;
    private double perMinute0To200;
    private double perMinuteAbove200;

}
