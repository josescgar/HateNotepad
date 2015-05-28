package com.escobeitor.hatenotepad.controller;

import com.escobeitor.hatenotepad.main.HatenoteConfiguration;
import com.escobeitor.hatenotepad.main.NoteListDto;
import com.escobeitor.hatenotepad.model.HateNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import com.escobeitor.hatenotepad.repository.HateNoteRepository;
import com.escobeitor.hatenotepad.repository.HatefulPersonRepository;

/**
 * Controller for actions related to hate notes
 * Created by escobeitor on 27/05/15.
 */
@RestController
@RequestMapping(value = "/note")
public class HateNoteController {

    @Autowired
    HatefulPersonRepository hatefulPersonRepository;

    @Autowired
    HateNoteRepository hateNoteRepository;

    @Autowired
    HatenoteConfiguration hatenoteConfiguration;

    @RequestMapping(value = "/{person}/{page}", method = RequestMethod.GET)
    public @ResponseBody NoteListDto getForPerson(@PathVariable String person, @PathVariable int page) {

        if(!hatefulPersonRepository.exists(person)) {
            throw new RuntimeException("No person found with ID " + person);
        }

        PageRequest pageRequest = new PageRequest(page, hatenoteConfiguration.getNotesPerPage());

        Page<HateNote> notes = hateNoteRepository.findByHatefulPerson(person, pageRequest);

        return new NoteListDto(notes.getNumber(), (int) notes.getTotalElements(), notes.getContent());
    }

    @RequestMapping(value = "/{person}/delete", method = RequestMethod.DELETE)
    public void delete(@PathVariable String person) {

        if(!hatefulPersonRepository.exists(person)) {
            throw new RuntimeException("No person found with ID " + person);
        }

        hateNoteRepository.deleteByHatefulPerson(person);
    }
}