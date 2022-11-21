package pl.dpotyralski.javaintegrationtestintroduction;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import pl.dpotyralski.javaintegrationtestintroduction.movie.MovieType;
import pl.dpotyralski.javaintegrationtestintroduction.movie.protocol.MovieRequest;

import java.io.IOException;

@JsonTest
class _8_JsonTestIntegrationTest {

    @Autowired
    private JacksonTester<MovieRequest> json;

    @Test
    void shouldSerializeMovieRequest() throws IOException {
        //given
        MovieRequest movieRequest = new MovieRequest("Pulp Fiction", MovieType.REGULAR);

        //expect
        Assertions.assertThat(this.json.write(movieRequest)).hasJsonPathStringValue("$.title");
        Assertions.assertThat(this.json.write(movieRequest)).extractingJsonPathStringValue("$.title").isEqualTo("Pulp Fiction");
    }

    @Test
    void shouldDeserializeMovieRequest() throws IOException {
        //given
        String request = """
                {
                "title":"Green Mile",
                "type": "OLD"
                }
                """;
        MovieRequest expected = new MovieRequest("Green Mile", MovieType.OLD);

        //when
        MovieRequest actual = this.json.parseObject(request);

        //expect
        Assertions.assertThat(actual).isEqualTo(expected);
    }

}
