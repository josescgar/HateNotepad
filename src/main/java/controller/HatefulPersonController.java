package controller;

import enums.EAssholeLevel;
import model.HatefulPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.HatefulPersonRepository;

/**
 * Actions related to Hateful people
 * Created by escobeitor on 27/05/15.
 */
@RestController
@RequestMapping("/person")
public class HatefulPersonController {

    @Autowired
    HatefulPersonRepository hatefulPersonRepository;

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public @ResponseBody HatefulPerson byEmail(@PathVariable(value = "email") String email) {
        return hatefulPersonRepository.findByEmail(email);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(@RequestParam(value = "email", required = true) String email,
                         @RequestParam(value = "nickname", required = true) String nickname,
                         @RequestParam(value = "assholeness", required = true) EAssholeLevel assholeLevel) {

        if(hatefulPersonRepository.findByEmail(email) != null) {
            throw new RuntimeException("User with email " + email + " already exists");
        }

        HatefulPerson person = new HatefulPerson(nickname, email, assholeLevel);
        person = hatefulPersonRepository.save(person);

        return person.getId();
    }
}
