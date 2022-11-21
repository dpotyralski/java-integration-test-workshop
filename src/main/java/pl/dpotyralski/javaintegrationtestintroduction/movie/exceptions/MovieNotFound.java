package pl.dpotyralski.javaintegrationtestintroduction.movie.exceptions;

import pl.dpotyralski.javaintegrationtestintroduction.infrastructure.exceptions.ApplicationException;
import pl.dpotyralski.javaintegrationtestintroduction.infrastructure.exceptions.ErrorCodes;

import java.text.MessageFormat;

public class MovieNotFound extends ApplicationException {

    public MovieNotFound(Long id) {
        super(ErrorCodes.MOVIE_NOT_FOUND_CODE, MessageFormat.format("Movie with id: {0} could not be found", id));
    }

}
