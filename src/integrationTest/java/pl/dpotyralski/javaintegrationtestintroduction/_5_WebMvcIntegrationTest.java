package pl.dpotyralski.javaintegrationtestintroduction;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.dpotyralski.javaintegrationtestintroduction.movie.MovieController;
import pl.dpotyralski.javaintegrationtestintroduction.movie.MovieFacade;
import pl.dpotyralski.javaintegrationtestintroduction.movie.protocol.MovieRequest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MovieController.class)
class _5_WebMvcIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovieFacade movieFacade;

    @Test
    void shouldCreateMovie() throws Exception {
        mockMvc
                .perform(
                        post("/api/v1//movies/")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(new MovieRequest("title", null))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("code", is(500)))
                .andExpect(jsonPath("message", is("Validation failed")))
                .andExpect(jsonPath("fieldErrors[0].field", is("type")))
                .andExpect(jsonPath("fieldErrors[0].message", is("must not be null")));
    }

}
