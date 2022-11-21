package pl.dpotyralski.javaintegrationtestintroduction.movie.protocol;

import lombok.Value;
import pl.dpotyralski.javaintegrationtestintroduction.movie.MovieType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Value
public class MovieRequest {

    @NotEmpty
    String title;

    @NotNull
    MovieType type;

}
