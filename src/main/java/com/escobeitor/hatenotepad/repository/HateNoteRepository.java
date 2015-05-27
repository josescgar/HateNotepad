package com.escobeitor.hatenotepad.repository;

import com.escobeitor.hatenotepad.model.HateNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.Serializable;

/**
 * HateNote database repository
 * Created by escobeitor on 27/05/15.
 */
public interface HateNoteRepository extends MongoRepository<HateNote, Serializable> {

    /**
     * Returns all hate notes directed to the given person with pagination
     * @param hatefulPerson The hateful person id
     * @param page Results page number
     * @return
     */
    Page<HateNote> findByHatefulPerson(String hatefulPerson, Pageable page);

    /**
     * Deletes all notes for a given person
     * @param hatefulPerson The person's id
     */
    long deleteByHatefulPerson(String hatefulPerson);
}
