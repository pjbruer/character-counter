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

        return new CharacterCounterResponse(findWordsThatStartBeginCharacter(text, character));
    }

    public CharacterCounterResponse findWordsBody(CharacterCounterRequest body) {

        return new CharacterCounterResponse(findWordsThatStartBeginCharacter(body.text, body.character));
    }


    // TODO - esa rosor apa sms aha bob, on esa(or often first word) the method failes and counts as valid, why?
    // TODO - utilize toLowerCase() and objectMapper

    public Integer findWordsThatStartBeginCharacter(String text, Character character){
        List<String> wordsFromText = Arrays.asList(text.split(" "));
        List<Character> amountOfWordsStartingWithCharacter = new ArrayList<>();

        int match = 1;
        for (String word:wordsFromText) {
            match = Character.compare(word.charAt(0), character);
            if (match == 0) amountOfWordsStartingWithCharacter.add(word.charAt(0));
        }
        return amountOfWordsStartingWithCharacter.size();
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
