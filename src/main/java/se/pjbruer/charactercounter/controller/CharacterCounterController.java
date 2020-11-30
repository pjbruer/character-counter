package se.pjbruer.charactercounter.controller;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.pjbruer.charactercounter.model.CharacterCounterRequest;
import se.pjbruer.charactercounter.model.CharacterCounterResponse;
import se.pjbruer.charactercounter.service.CharacterCounterService;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
@Validated
public class CharacterCounterController {

    CharacterCounterService counterService;

    @Autowired
    public CharacterCounterController(CharacterCounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping("/findWordsParams")
    @ResponseBody
    public ResponseEntity<CharacterCounterResponse> findWordsParams(@RequestParam("text") @NotNull @Length(min = 1) String text,
                                                                    @RequestParam("character") @NotNull Character character){

        CharacterCounterResponse result = counterService.findWordsParams(text, character);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/findWordsBody")
    @ResponseBody
    public ResponseEntity<CharacterCounterResponse> findWordsBody(@Valid @RequestBody CharacterCounterRequest body){
        CharacterCounterResponse result = counterService.findWordsBody(body);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}


