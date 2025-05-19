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
public class Unlimited {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String imgPath;
    private double cost;

}
