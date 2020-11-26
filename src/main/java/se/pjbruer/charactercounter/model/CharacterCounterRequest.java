package se.pjbruer.charactercounter.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

public class CharacterCounterRequest {

    @NonNull
    public final String text;

    @NonNull
    public final Character character;

    @JsonCreator
    public CharacterCounterRequest(@JsonProperty("text") String text,
                                   @JsonProperty("character") Character character) {
        this.text = text;
        this.character = character;
    }
}
