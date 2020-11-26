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
public class CharacterCounterControllerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CharacterCounterService counterService;

    @Test
    void shouldAcceptParamsAndReturnResult() throws Exception {
        String text = "Hell";
        Character character = 'o';

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/findCharacterInText")
                .contentType(MediaType.APPLICATION_JSON)
                .param("text", text)
                .param("character", String.valueOf(character));

        MvcResult result = mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("{\"result\":\"Hello\"}", result.getResponse().getContentAsString());

    }
}
