package se.pjbruer.charactercounter.service;

import org.springframework.stereotype.Service;
import se.pjbruer.charactercounter.model.CharacterCounterRequest;
import se.pjbruer.charactercounter.model.CharacterCounterResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CharacterCounterService {
    public CharacterCounterResponse findWordsParams(String text, Character character) {

        return new CharacterCounterResponse(findWordsInTextThatBeginWithCharacter(text, character));
    }

    public CharacterCounterResponse findWordsBody(CharacterCounterRequest body) {

        return new CharacterCounterResponse(findWordsInTextThatBeginWithCharacter(body.text, body.character));
    }

    // TODO - Insecure deserialization often leads to remote code execution

    public Integer findWordsInTextThatBeginWithCharacter(String text, Character character){
        List<String> wordsFromText = splitTextIntoWords(text.toUpperCase());
        List<Character> amountOfWordsStartingWithCharacter = new ArrayList<>();

        for (String word:wordsFromText) {
            if (comparison(word, character)) {
                amountOfWordsStartingWithCharacter.add(word.charAt(0));
            }
        }
        return amountOfWordsStartingWithCharacter.size();
    }

    public List<String> splitTextIntoWords(String text) {
        return Arrays.asList(text.split(" "));
    }

    public boolean comparison(String word, Character character) {
        return Character.valueOf(word.charAt(0)).equals(Character.toUpperCase(character));
    }

    /*public Integer findAmountOfWordsThatBeginWithCharacter(String text, Character character){
        List<Integer> amountOfWords = new ArrayList<>();

        int index = text.indexOf(character);
        while (index >= 0) {
            amountOfWords.add(index);
            index = text.indexOf(" "+ character, index + 1);
        }
        return amountOfWords.size();
    }*/
}
