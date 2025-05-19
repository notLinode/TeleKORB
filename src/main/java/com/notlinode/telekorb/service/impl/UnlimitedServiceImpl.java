package com.notlinode.telekorb.service.impl;

import com.notlinode.telekorb.model.Unlimited;
import com.notlinode.telekorb.repository.UnlimitedRepository;
import com.notlinode.telekorb.service.UnlimitedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnlimitedServiceImpl implements UnlimitedService {

    @Autowired
    UnlimitedRepository unlimitedRepo;

    @Override
    public List<Unlimited> getAll() {
        var unlimiteds = unlimitedRepo.findAll();
        List<Unlimited> unlimitedList = new ArrayList<>();
        unlimiteds.forEach(unlimitedList::add);
        return unlimitedList;
    }

}
