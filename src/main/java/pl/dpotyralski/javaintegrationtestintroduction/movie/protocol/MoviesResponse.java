package pl.dpotyralski.javaintegrationtestintroduction.movie.protocol;

import lombok.Value;

import java.util.List;

@Value
public class MoviesResponse {

    List<MovieResponse> movies;

    @Value
    public static class MovieResponse {
        Long id;
        String title;
    }

}
