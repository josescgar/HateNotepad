package com.escobeitor.hatenotepad.repository;

import com.escobeitor.hatenotepad.model.HatefulPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Hateful people database repository
 * Created by escobeitor on 27/05/15.
 */
public interface HatefulPersonRepository extends MongoRepository<HatefulPerson, Serializable> {

    /**
     * Finds a {@link HatefulPerson} by its email
     * @param email Person's email
     * @return
     */
    HatefulPerson findByEmail(String email);
}
