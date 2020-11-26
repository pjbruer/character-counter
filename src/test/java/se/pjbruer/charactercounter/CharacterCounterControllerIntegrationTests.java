package se.pjbruer.charactercounter;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import se.pjbruer.charactercounter.controller.CharacterCounterController;
import se.pjbruer.charactercounter.model.CharacterCounterResponse;
import se.pjbruer.charactercounter.service.CharacterCounterService;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CharacterCounterControllerIntegrationTests {

    @InjectMocks
    CharacterCounterController characterCounterController;

    @Mock
    CharacterCounterService characterCounterService;


    @Before
    public void setup() throws Exception {
        characterCounterService = Mockito.mock(CharacterCounterService.class);
        characterCounterController = new CharacterCounterController(characterCounterService);

    }

    @Test
    void shouldReturnCharacterCounterResponseFromService() {

        String text = "abba rosor apa sms aha bob";
        Character character = 'a';

        CharacterCounterResponse response = new CharacterCounterResponse(3);

        Mockito.when(characterCounterService.findWords(text, character)).thenReturn(response);

        ResponseEntity<CharacterCounterResponse> result = characterCounterController.findWords(text, character);

        assertEquals(3, Objects.requireNonNull(result.getBody()).getResult());

    }
}