package se.pjbruer.charactercounter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import se.pjbruer.charactercounter.model.CharacterCounterRequest;
import se.pjbruer.charactercounter.model.CharacterCounterResponse;
import se.pjbruer.charactercounter.service.CharacterCounterService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterCounterServiceUnitTest {

    CharacterCounterService characterCounterService;

    Character upperCaseCharacter = 'A';
    Character lowerCaseCharacter = 'a';
    String text = "well Cross-Site Scripting XSS allows attackers to execute scripts in the victim’s browser which " +
                    "can hijack user sessions, deface web sites, or redirect the user to malicious sites";

    public CharacterCounterServiceUnitTest() {this.characterCounterService = new CharacterCounterService();}

    @Test
    public void shouldFindWordsThatStartBeginCharacterLowerCase(){
        int result = characterCounterService.findWordsInTextThatBeginWithCharacter(text, lowerCaseCharacter);

        assertEquals(result, 2);
    }

    @Test
    public void shouldFindWordsThatStartBeginCharacterUpperCase(){
        int result = characterCounterService.findWordsInTextThatBeginWithCharacter(text, upperCaseCharacter);

        assertEquals(result, 2);
    }

    @Test
    public void shouldFindWordsAndReturnCharacterCounterResponseClassParams(){
        CharacterCounterResponse result = characterCounterService.findWordsParams(text, lowerCaseCharacter);

        Assert.assertSame(result.getClass(), CharacterCounterResponse.class);
        assertEquals(result.getResult(), 2);

    }

    @Test
    public void shouldFindWordsAndReturnCharacterCounterResponseClassBody(){
        CharacterCounterResponse result = characterCounterService.findWordsBody(new CharacterCounterRequest(text, lowerCaseCharacter));

        Assert.assertSame(result.getClass(), CharacterCounterResponse.class);
        assertEquals(result.getResult(), 2);

    }
}
