package pl.dpotyralski.javaintegrationtestintroduction.movie;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MovieRepository {

    Optional<Movie> findById(long id);

    Optional<Movie> findByTitle(String title);

    List<Movie> findAllByIdIn(Set<Long> ids);

    Movie save(Movie movie);

    PageImpl<Movie> findAll(Pageable pageable);

}
