package com.notlinode.telekorb.repository;

import com.notlinode.telekorb.model.PhoneNumber;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, String> {

    Iterable<PhoneNumber> findAllByIsNiceIsTrue();

    Optional<PhoneNumber> findFirstByIsNiceIsFalse();

}
