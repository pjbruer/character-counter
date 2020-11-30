package se.pjbruer.charactercounter;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import se.pjbruer.charactercounter.service.CharacterCounterService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CharacterCounterControllerApiTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CharacterCounterService counterService;

    @Autowired
    private ObjectMapper objectMapper;

    String URL_FOR_FIND_WORDS_PARAMS = "/api/findWordsParams";
    String URL_FOR_FIND_WORDS_BODY = "/api/findWordsBody";
    String URL_FOR_NON_EXISTING_ENDPOINT = "/api/notRightPath";

    String correctJson = "{\"text\":\"abba rosor apa sms aha bob\", \"character\":\"a\"}";
    String brokenJson = "\"text\":\"abba rosor apa sms aha bob\", \"character\":\"a\"}";
    String textValidationError = "\"text\":\"\", \"character\":\"a\"}";
    String characterValidationError = "\"text\":\"abba rosor apa sms aha bob\", \"character\":\"\"}";

    String text = "abba rosor apa sms aha bob";
    Character character = 'a';


    // HAPPY CASES

    @Test
    void shouldAcceptParams_ThenReturnsResultAndStatus200() throws Exception {
        RequestBuilder request = post(URL_FOR_FIND_WORDS_PARAMS)
                .contentType(MediaType.APPLICATION_JSON)
                .param("text", text)
                .param("character", String.valueOf(character));

        MvcResult result = mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("{\"result\":3}", result.getResponse().getContentAsString());
    }

    @Test
    void shouldAcceptBody_ThenReturnsResultAndStatus200() throws Exception {
        RequestBuilder request = post(URL_FOR_FIND_WORDS_BODY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(correctJson);

        MvcResult result = mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("{\"result\":3}", result.getResponse().getContentAsString());
    }

    // SAD CASES

    @Test
    void shouldNotAcceptParams_ThenReturnsStatus400() throws Exception {
        RequestBuilder request = post(URL_FOR_FIND_WORDS_PARAMS)
                .contentType(MediaType.APPLICATION_JSON)
                .param("text", text)
                .param("wrongParam", String.valueOf(character));

        MvcResult result = mvc.perform(request)
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    void shouldNotAcceptBrokenJson_ThenReturnsStatus400() throws Exception {
        RequestBuilder request = post(URL_FOR_FIND_WORDS_BODY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(brokenJson);

        MvcResult result = mvc.perform(request)
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    void shouldNotAcceptParamPath_ThenReturnsStatus404() throws Exception {
        RequestBuilder request = post(URL_FOR_NON_EXISTING_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("text", text)
                .param("character", String.valueOf(character));

        MvcResult result = mvc.perform(request)
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
    }

    @Test
    void shouldNotAcceptBodyPath_ThenReturnsStatus404() throws Exception {
        RequestBuilder request = post(URL_FOR_NON_EXISTING_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(brokenJson);

        MvcResult result = mvc.perform(request)
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
    }

    @Test
    void whenTextInputIsInvalid_thenReturnsStatus400() throws Exception {
        String body = objectMapper.writeValueAsString(textValidationError);

        mvc.perform(post(URL_FOR_FIND_WORDS_BODY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(textValidationError))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenCharacterInputIsInvalid_thenReturnsStatus400() throws Exception {
        String body = objectMapper.writeValueAsString(characterValidationError);

        mvc.perform(post(URL_FOR_FIND_WORDS_BODY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(characterValidationError))
                .andExpect(status().isBadRequest());
    }
}
