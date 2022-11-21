package pl.dpotyralski.javaintegrationtestintroduction.movie;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Transactional
@AllArgsConstructor
public class MovieFacade {

    private final MovieCreator movieCreator;
    private final MovieSearch movieSearch;

    @Transactional(readOnly = true)
    public MovieDto findById(long id) {
        return movieSearch.findById(id);
    }

    @Transactional(readOnly = true)
    public List<MovieDto> findAllByIds(Set<Long> ids) {
        return movieSearch.findAllByIds(ids);
    }

    @Transactional(readOnly = true)
    public List<MovieDto> findAll(PageRequest pageRequest) {
        return movieSearch.findAll(pageRequest);
    }

    public MovieDto addMovie(String title, MovieType movieType) {
        Optional<MovieDto> alreadyExistingByTitle = movieSearch.findByTitle(title);

        if (alreadyExistingByTitle.isEmpty()) {
            return movieCreator.createMovie(title, movieType);
        }
        log.info("Already existing, returning: " + title);
        return alreadyExistingByTitle.get();
    }
}
