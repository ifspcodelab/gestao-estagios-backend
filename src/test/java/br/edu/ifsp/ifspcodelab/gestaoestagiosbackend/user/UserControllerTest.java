package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void createUser() throws Exception {
        mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"email\": \"john@email.com\", \"password\": \"123456\" }")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.email").value("john@email.com"))
            .andExpect(jsonPath("$.id").exists())
            .andDo(print());
    }

    @Test
    public void createUserConflict() throws Exception {
        userRepository.save(new User("john@email.com", "123456"));

        mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"email\": \"john@email.com\", \"password\": \"123456\" }")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isConflict())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("User already exists"))
            .andExpect(jsonPath("$.violations[0].name").value("email"))
            .andExpect(jsonPath("$.violations[0].reason").value("User already exists with email john@email.com"))
            .andDo(print());
    }

    @Test
    public void createUserBadRequestValidation() throws Exception {
        mockMvc.perform(post("/api/v1/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"email\": \"johnemail.com\", \"password\": \"123456\" }")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("Your request parameters didn't validate"))
            .andExpect(jsonPath("$.violations[0].name").value("email"))
            .andExpect(jsonPath("$.violations[0].reason").value("must be a well-formed email address"))
            .andDo(print());
    }
}
