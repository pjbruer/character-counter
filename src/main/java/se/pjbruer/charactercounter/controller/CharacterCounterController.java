package se.pjbruer.charactercounter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pjbruer.charactercounter.model.CharacterCounterRequest;
import se.pjbruer.charactercounter.model.CharacterCounterResponse;
import se.pjbruer.charactercounter.service.CharacterCounterService;
import se.pjbruer.charactercounter.util.Converter;

@RestController
@RequestMapping("/api")
public class CharacterCounterController {

    CharacterCounterService counterService;

    @Autowired
    public CharacterCounterController(CharacterCounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping("/findWordsParams")
    @ResponseBody
    public ResponseEntity<CharacterCounterResponse> findWords(@RequestParam String text, @RequestParam Character character){
        CharacterCounterResponse result = counterService.findWords(text, character);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/findWordsBody")
    @ResponseBody
    public ResponseEntity<CharacterCounterResponse> findWords(@RequestBody CharacterCounterRequest body){
        CharacterCounterResponse result = counterService.findWords(Converter.toCharacterCounterFromCharacterCounterRequest(body));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}


