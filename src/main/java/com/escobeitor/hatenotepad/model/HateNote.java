package com.escobeitor.hatenotepad.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Represents a hate note regarding a hateful person
 * Created by escobeitor on 27/05/15.
 */
@Document
public class HateNote {

    @Id
    private String id;

    @NotEmpty
    private String message;

    private Date created;

    @Indexed
    private String hatefulPerson;

    public HateNote(String message, Date created, String hatefulPerson) {
        this.message = message;
        this.created = created;
        this.hatefulPerson = hatefulPerson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getHatefulPerson() {
        return hatefulPerson;
    }

    public void setHatefulPerson(String hatefulPerson) {
        this.hatefulPerson = hatefulPerson;
    }
}
