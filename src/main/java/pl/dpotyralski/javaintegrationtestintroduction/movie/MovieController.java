package pl.dpotyralski.javaintegrationtestintroduction.movie;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dpotyralski.javaintegrationtestintroduction.movie.protocol.MovieRequest;
import pl.dpotyralski.javaintegrationtestintroduction.movie.protocol.MoviesResponse;

import javax.validation.Valid;
import java.util.List;

import static pl.dpotyralski.javaintegrationtestintroduction.movie.protocol.MoviesResponse.MovieResponse;

@RestController
@RequestMapping("/api/v1/movies")
@AllArgsConstructor
@Slf4j
public class MovieController {

    private final MovieFacade movieFacade;

    @PostMapping
    public ResponseEntity<MovieResponse> storeMovie(@RequestBody @Valid MovieRequest movieRequest) {
        log.info("Storing movie: " + movieRequest.toString());
        MovieDto movieDto = movieFacade.addMovie(movieRequest.getTitle(), movieRequest.getType());
        return ResponseEntity.status(HttpStatus.CREATED).body(new MovieResponse(movieDto.getId(), movieDto.getTitle()));
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Get movie from store")
    public MovieResponse getMovie(@PathVariable("id") Long id) {
        log.info("Geeting movie by id: " + id);
        MovieDto byId = movieFacade.findById(id);
        return createMovieResponse(byId);
    }

    @GetMapping
    @ApiOperation(value = "List of movies in the store")
    public MoviesResponse getMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        List<MovieResponse> collect = movieFacade.findAll(PageRequest.of(page, size)).stream()
                .map(this::createMovieResponse)
                .toList();

        return new MoviesResponse(collect);
    }

    private MovieResponse createMovieResponse(MovieDto movieDto) {
        return new MovieResponse(movieDto.getId(), movieDto.getTitle());
    }

}
