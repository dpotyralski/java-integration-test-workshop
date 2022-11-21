package pl.dpotyralski.javaintegrationtestintroduction.rental;


import lombok.AllArgsConstructor;
import pl.dpotyralski.javaintegrationtestintroduction.infrastructure.TimeProvider;
import pl.dpotyralski.javaintegrationtestintroduction.movie.MovieDto;
import pl.dpotyralski.javaintegrationtestintroduction.movie.MovieFacade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@AllArgsConstructor
class RentalCreator {

    private final MovieFacade movieFacade;
    private final RentalPriceCalculator rentalPriceCalculator;
    private final RentalRepository rentalRepository;
    private final TimeProvider timeProvider;

    BigDecimal calculateRentalPrice(RentalPriceCalculateCommand rentalPriceCalculateCommand) {
        List<MovieDto> movies = movieFacade.findAllByIds(getMoviesIds(rentalPriceCalculateCommand.getDetails()));
        Map<Long, RentalDetail> moviesWithRentalDetails = transformToMoviesWithRentalDetailsMap(rentalPriceCalculateCommand.getDetails());

        return movies.stream().map(movie -> {
            long days = moviesWithRentalDetails.get(movie.getId()).getDays();
            return rentalPriceCalculator.calculateRentalPrice(days, movie.getMovieType());
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    List<RentalDto> rent(RentalCommand rentalCommand) {
        Long customerId = rentalCommand.getCustomerId();
        List<MovieDto> movies = movieFacade.findAllByIds(getMoviesIds(rentalCommand.getMovieDetails()));
        Map<Long, RentalDetail> moviesWithRentalDetails = transformToMoviesWithRentalDetailsMap(rentalCommand.getMovieDetails());

        Set<Rental> rentals = movies.stream()
                .map(movie -> createRental(customerId, movie, moviesWithRentalDetails.get(movie.getId()).getDays()))
                .collect(toSet());

        List<RentalDto> result = rentalRepository.saveAll(rentals).stream()
                .map(Rental::toDto)
                .toList();

        rentalRepository.flush();
        return result;
    }

    private Rental createRental(Long customerId, MovieDto movieDto, long days) {
        BigDecimal priceCalculated = rentalPriceCalculator.calculateRentalPrice(days, movieDto.getMovieType());
        LocalDate rentDate = timeProvider.now();
        return Rental.builder()
                .customerId(customerId)
                .movieId(movieDto.getId())
                .priceCalculated(priceCalculated)
                .rentDate(rentDate)
                .dueBy(rentDate.plusDays(days))
                .movieType(movieDto.getMovieType())
                .build();
    }

    private Map<Long, RentalDetail> transformToMoviesWithRentalDetailsMap(List<RentalDetail> rentalDetails) {
        return rentalDetails.stream()
                .collect(Collectors.toMap(RentalDetail::getMovieId, rentalDetail -> rentalDetail));
    }

    private Set<Long> getMoviesIds(List<RentalDetail> rentalDetails) {
        return rentalDetails.stream()
                .map(RentalDetail::getMovieId)
                .collect(toSet());
    }
}
