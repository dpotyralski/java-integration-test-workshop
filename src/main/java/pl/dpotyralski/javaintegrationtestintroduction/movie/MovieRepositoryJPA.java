package pl.dpotyralski.javaintegrationtestintroduction.movie;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

interface MovieRepositoryJPA extends JpaRepository<Movie, Long>, MovieRepository {

    Optional<Movie> findById(long id);

    Movie save(Movie movie);

    PageImpl<Movie> findAll(Pageable pageable);

    List<Movie> findAllByIdIn(Set<Long> ids);

}
