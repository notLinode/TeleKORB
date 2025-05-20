package com.notlinode.telekorb.service.impl;

import com.notlinode.telekorb.model.PhoneNumber;
import com.notlinode.telekorb.repository.PhoneNumberRepository;
import com.notlinode.telekorb.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Autowired
    PhoneNumberRepository phoneRepo;

    @Override
    public Optional<PhoneNumber> findByPhone(String phone) {
        return phoneRepo.findById(phone);
    }

    @Override
    public List<PhoneNumber> getAllNiceNumbers() {
        var phones = phoneRepo.findAllByIsNiceIsTrue();
        List<PhoneNumber> phoneList = new ArrayList<>();
        phones.forEach(phoneList::add);
        return phoneList;
    }

    @Override
    public Optional<PhoneNumber> getNextNotNiceNumber() {
        return phoneRepo.findFirstByIsNiceIsFalse();
    }

    @Override
    public void deleteByPhone(String phone) {
        phoneRepo.deleteById(phone);
    }

}
