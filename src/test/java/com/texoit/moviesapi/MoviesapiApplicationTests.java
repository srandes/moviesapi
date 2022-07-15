package com.texoit.moviesapi;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;



@SpringBootTest
@AutoConfigureMockMvc
class MoviesapiApplicationTests {
	
	@Autowired
    protected MockMvc mvc;

	@Test
	/* Test implemented according to the data sent */
	void shouldGetAwardIntervalSuccess() throws Exception {
	  mvc.perform(get("/movies/award-interval"))
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("min[0].producer").value("Bo Derek"))
		.andExpect(jsonPath("max[0].producer").value("Bo Derek"))
	    .andDo(print());
	}
}
