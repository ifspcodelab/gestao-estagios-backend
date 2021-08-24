package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StateControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StateRepository stateRepository;

    @BeforeEach
    @AfterEach
    public void setUp() {
        stateRepository.deleteAll();
    }

    @Test
    public void getStates() throws Exception {
        State state = stateRepository.save(new State("TS", "Test State"));
        State state2 = stateRepository.save(new State("TA", "Test State 2"));

        MvcResult result = mockMvc.perform(get("/api/v1/states")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.*", isA(ArrayList.class)))
            .andExpect(jsonPath("$.*", hasSize(2)))
            .andExpect(jsonPath("$[0].abbreviation").value(state.getAbbreviation()))
            .andExpect(jsonPath("$[1].abbreviation").value(state2.getAbbreviation()))
            .andReturn();

        assertThat(result).isNotNull();
    }
}
