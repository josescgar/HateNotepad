package repository;

import model.HateNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * HateNote database repository
 * Created by escobeitor on 27/05/15.
 */
@Repository
public interface HateNoteRepository extends MongoRepository<HateNote, Serializable> {

    /**
     * Returns all hate notes directed to the given person with pagination
     * @param person The hateful person id
     * @param page Results page number
     * @return
     */
    Page<HateNote> findByHatefulPerson(String person, Pageable page);

    /**
     * Deletes all notes for a given person
     * @param person The person's id
     */
    void deleteByHatefulPerson(String person);
}
