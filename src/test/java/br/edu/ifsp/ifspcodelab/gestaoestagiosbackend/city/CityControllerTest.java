package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.State;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.StateRepository;
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
public class CityControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;

    @BeforeEach
    public void setUp() {
        cityRepository.deleteAll();
        stateRepository.deleteAll();
    }

    @Test
    public void getCities() throws Exception {
        State state = stateRepository.save(new State("TS", "Test State"));
        City city = cityRepository.save(new City("Test City", state));

        MvcResult result = mockMvc.perform(get("/api/v1/states/{stateAbbreviation}/cities", state.getAbbreviation())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.*", isA(ArrayList.class)))
            .andExpect(jsonPath("$.*", hasSize(1)))
            .andExpect(jsonPath("$[0].id").value(city.getId().toString()))
            .andReturn();

        assertThat(result).isNotNull();
    }
}
