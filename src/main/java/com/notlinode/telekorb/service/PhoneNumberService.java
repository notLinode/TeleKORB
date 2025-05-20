package com.notlinode.telekorb.service;

import com.notlinode.telekorb.model.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface PhoneNumberService {

    Optional<PhoneNumber> findByPhone(String phone);

    List<PhoneNumber> getAllNiceNumbers();

    Optional<PhoneNumber> getNextNotNiceNumber();

    void deleteByPhone(String phone);

}
