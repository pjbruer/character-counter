package se.pjbruer.charactercounter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import se.pjbruer.charactercounter.model.CharacterCounterRequest;
import se.pjbruer.charactercounter.model.CharacterCounterResponse;
import se.pjbruer.charactercounter.service.CharacterCounterService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterCounterServiceUnitTest {

    CharacterCounterService characterCounterService = new CharacterCounterService();

    @Test
    public void shouldfindAmountOfWordsThatBeginWithCharacter(){
        String text = "abba rosor apa sms aha bob";
        Character character = 'a';

        int result = characterCounterService.findAmountOfWordsThatBeginWithCharacter(text, character);
        assertEquals(result, 3);
    }

    @Test
    public void shouldFindWordsAndReturnCharacterCounterResponseClassParams(){
        String text = "abba rosor apa sms aha bob";
        Character character = 'a';

        CharacterCounterResponse result = characterCounterService.findWords(text, character);

        Assert.assertSame(result.getClass(), CharacterCounterResponse.class);
    }

    @Test
    public void shouldFindWordsAndReturnCharacterCounterResponseClassBody(){
        CharacterCounterRequest body = new CharacterCounterRequest("abba rosor apa sms aha bob", 'a');

        CharacterCounterResponse result = characterCounterService.findWords(body.text, body.character);

        Assert.assertSame(result.getClass(), CharacterCounterResponse.class);
    }
}
