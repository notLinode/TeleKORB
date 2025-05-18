package com.notlinode.telekorb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Date createdAt;

    private String name;
    private String city;
    private String mail;
    private String phoneNum;
    private String payment;

}
