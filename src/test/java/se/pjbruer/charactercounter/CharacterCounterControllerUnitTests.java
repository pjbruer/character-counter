package se.pjbruer.charactercounter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import se.pjbruer.charactercounter.controller.CharacterCounterController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CharacterCounterController.class)
class CharacterCounterControllerUnitTests {

	@Autowired
	MockMvc mvc;

	// POSITIVE CASES

	@Test
	void shouldReturnHttpStatus200() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/home");

		MvcResult result = mvc.perform(request)
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		assertEquals(200, result.getResponse().getStatus());
	}

	// NEGATIVE CASES

	@Test
	void shouldNotReturnHomepageAndRturnHttpStatus400() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/otherPath");

		MvcResult result = mvc.perform(request)
				.andDo(print())
				.andExpect(status().is4xxClientError())
				.andReturn();

		assertEquals(404, result.getResponse().getStatus());
	}

}
