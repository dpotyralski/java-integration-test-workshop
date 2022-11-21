package pl.dpotyralski.javaintegrationtestintroduction.movie;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import pl.dpotyralski.javaintegrationtestintroduction.movie.exceptions.MovieNotFound;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
class MovieSearch {

    private final MovieRepository movieRepository;

    List<MovieDto> findAll(Pageable pageable) {
        return movieRepository.findAll(pageable).stream()
                .map(Movie::toDto)
                .toList();
    }

    Optional<MovieDto> findByTitle(String title) {
        return movieRepository.findByTitle(title)
                .map(Movie::toDto);
    }

    MovieDto findById(long id) {
        return movieRepository.findById(id)
                .map(Movie::toDto)
                .orElseThrow(() -> new MovieNotFound(id));
    }

    List<MovieDto> findAllByIds(Set<Long> ids) {
        return movieRepository.findAllByIdIn(ids).stream()
                .map(Movie::toDto)
                .toList();
    }

}
