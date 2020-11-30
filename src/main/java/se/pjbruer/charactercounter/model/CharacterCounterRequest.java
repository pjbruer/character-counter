package se.pjbruer.charactercounter.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class CharacterCounterRequest {

    @NotEmpty()
    @Length(min = 1)
    public final String text;

    @NotNull
    public final Character character;

    @JsonCreator
    public CharacterCounterRequest(@JsonProperty("text") String text,
                                   @JsonProperty("character") Character character) {
        this.text = text;
        this.character = character;
    }
}
