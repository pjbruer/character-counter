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
    public void shouldFindWordsThatStartBeginCharacter(){
        String text = "besk rosor apa sms aha bob";
        Character character = 'a';

        int result = characterCounterService.findWordsThatStartBeginCharacter(text, character);
        assertEquals(result, 2);
    }

    @Test
    public void shouldFindWordsAndReturnCharacterCounterResponseClassParams(){
        String text = "abba rosor apa sms aha bob";
        Character character = 'a';

        CharacterCounterResponse result = characterCounterService.findWordsParams(text, character);

        Assert.assertSame(result.getClass(), CharacterCounterResponse.class);
    }

    @Test
    public void shouldFindWordsAndReturnCharacterCounterResponseClassBody(){
        CharacterCounterRequest body = new CharacterCounterRequest("abba rosor apa sms aha bob", 'a');

        CharacterCounterResponse result = characterCounterService.findWordsBody(body);

        Assert.assertSame(result.getClass(), CharacterCounterResponse.class);
    }
}
