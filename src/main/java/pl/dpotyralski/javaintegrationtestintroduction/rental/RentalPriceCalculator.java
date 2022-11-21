package pl.dpotyralski.javaintegrationtestintroduction.rental;

import pl.dpotyralski.javaintegrationtestintroduction.movie.MovieType;
import pl.dpotyralski.javaintegrationtestintroduction.movie.PriceType;

import java.math.BigDecimal;

import static pl.dpotyralski.javaintegrationtestintroduction.movie.PriceType.PREMIUM;


class RentalPriceCalculator {

    private final RentalProperties rentalProperties;

    RentalPriceCalculator(RentalProperties rentalProperties) {
        this.rentalProperties = rentalProperties;
    }

    BigDecimal calculateRentalPrice(long days, MovieType movieType) {
        MoviePriceCalculationType moviePriceCalculationType = MoviePriceCalculationType.resolveMoviePriceCalculationTypeByMovieType(movieType);
        return moviePriceCalculationType.getPriceCalculationStrategy()
                .calculate(resolvePriceValue(moviePriceCalculationType.getMovieType().getPriceType()), days);
    }

    BigDecimal calculateReturnChargePrice(long days, MovieType movieType) {
        MoviePriceCalculationType moviePriceCalculationType = MoviePriceCalculationType.resolveMoviePriceCalculationTypeByMovieType(movieType);
        return moviePriceCalculationType.getReturnPriceCalculateStrategy()
                .calculate(resolvePriceValue(moviePriceCalculationType.getMovieType().getPriceType()), days);
    }

    private BigDecimal resolvePriceValue(PriceType priceType) {
        return PREMIUM.equals(priceType) ? rentalProperties.getPremiumPrice() : rentalProperties.getBasicPrice();
    }

}
