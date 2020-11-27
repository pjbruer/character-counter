package se.pjbruer.charactercounter;


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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.pjbruer.charactercounter.service.CharacterCounterService;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    String URL_FOR_FIND_WORDS_PARAMS = "/api/findWordsParams";
    String URL_FOR_FIND_WORDS_BODY = "/api/findWordsBody";
    String URL_FOR_NON_EXISTING_ENDPOINT = "/api/notRightPath";

    String json = "{\"text\":\"abba rosor apa sms aha bob\", \"character\":\"a\"}";
    String brokenJson = "\"text\":\"abba rosor apa sms aha bob\", \"character\":\"a\"}";

    String text = "abba rosor apa sms aha bob";
    Character character = 'a';


    // HAPPY CASES

    @Test
    void shouldAcceptParamsAndReturnResultAndHttp200() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post(URL_FOR_FIND_WORDS_PARAMS)
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
    void shouldAcceptBodyAndReturnResultAndHttp200() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post(URL_FOR_FIND_WORDS_BODY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        MvcResult result = mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("{\"result\":3}", result.getResponse().getContentAsString());
    }

    // SAD CASES

    @Test
    void shouldNotAcceptParamsAndReturnHttp400() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post(URL_FOR_FIND_WORDS_PARAMS)
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
    void shouldNotAcceptBrokenJsonAndReturnHttp400() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post(URL_FOR_FIND_WORDS_BODY)
                .contentType(MediaType.APPLICATION_JSON)
                .content(brokenJson);

        MvcResult result = mvc.perform(request)
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(400, result.getResponse().getStatus());
    }

    @Test
    void shouldNotAcceptParamPathAndReturnHttp404() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post(URL_FOR_NON_EXISTING_ENDPOINT)
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
    void shouldNotAcceptBodyPathAndReturnHttp404() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post(URL_FOR_NON_EXISTING_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        MvcResult result = mvc.perform(request)
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
    }
}
