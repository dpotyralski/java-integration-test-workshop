package pl.dpotyralski.javaintegrationtestintroduction.movie;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class MovieCreator {

    private final MovieRepository movieRepository;

    MovieDto createMovie(String title, MovieType movieType) {
        return movieRepository.save(new Movie(title, movieType)).toDto();
    }
}
