package pl.dpotyralski.javaintegrationtestintroduction.movie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MovieConfigurator {

    @Bean
    MovieRepository movieRepository(MovieRepositoryJPA movieRepositoryJPA) {
        return movieRepositoryJPA;
    }

    @Bean
    MovieCreator movieCreator(MovieRepository movieRepository) {
        return new MovieCreator(movieRepository);
    }

    @Bean
    MovieSearch movieSearch(MovieRepository movieRepository) {
        return new MovieSearch(movieRepository);
    }

    @Bean
    MovieFacade movieFacade(MovieCreator movieCreator, MovieSearch movieSearch) {
        return new MovieFacade(movieCreator, movieSearch);
    }

}
