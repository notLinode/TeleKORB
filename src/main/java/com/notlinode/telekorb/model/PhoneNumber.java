package com.notlinode.telekorb.model;

import jakarta.persistence.Entity;
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
public class PhoneNumber {

    @Id
    private String phone;
    private double cost;

    @Builder.Default
    private boolean isNice = false;

    public String getFormattedPhone() {
        return String.format("+%s (%s) %s %s-%s", phone.charAt(0), phone.substring(1,4), phone.substring(4,7), phone.substring(7,9), phone.substring(9,11));
    }

}
