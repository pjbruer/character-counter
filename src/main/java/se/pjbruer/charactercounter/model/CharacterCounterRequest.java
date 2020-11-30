package se.pjbruer.charactercounter.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CharacterCounterRequest {

    @NotEmpty
    @Size(min=1, message="Text should have at least 1 letter")
    public final String text;

    @NotEmpty
    @Size(min = 1, max = 1, message="Character should only have 1 character")
    public final Character character;

    @JsonCreator
    public CharacterCounterRequest(@JsonProperty("text") String text,
                                   @JsonProperty("character") Character character) {
        this.text = text;
        this.character = character;
    }
}
