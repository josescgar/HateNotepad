package model;

import enums.EAssholeLevel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Represents a hateful person
 * Created by escobeitor on 27/05/15.
 */
@Document
public class HatefulPerson {

    @Id
    private String id;

    @NotNull
    private String nickname;

    @Indexed(unique = true)
    private String email;

    @NotNull
    private EAssholeLevel assholeLevel;

    public HatefulPerson(String nickname, String email, EAssholeLevel assholeLevel) {
        this.nickname = nickname;
        this.email = email;
        this.assholeLevel = assholeLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EAssholeLevel getAssholeLevel() {
        return assholeLevel;
    }

    public void setAssholeLevel(EAssholeLevel assholeLevel) {
        this.assholeLevel = assholeLevel;
    }
}
