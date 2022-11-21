package pl.dpotyralski.javaintegrationtestintroduction.rental;

import pl.dpotyralski.javaintegrationtestintroduction.movie.MovieType;

import java.math.BigDecimal;
import java.util.stream.Stream;

enum MoviePriceCalculationType {

    PREMIUM_NEW_RELEASE(MovieType.NEW_RELEASE, PriceCalculationStrategy.PREMIUM, PriceCalculationStrategy.RETURN),
    BASIC_REGULAR(MovieType.REGULAR, new BasicPriceCalculateStrategy(3), PriceCalculationStrategy.RETURN),
    BASIC_OLD(MovieType.OLD, new BasicPriceCalculateStrategy(5), PriceCalculationStrategy.RETURN);

    private MovieType movieType;
    private PriceCalculationStrategy priceCalculateStrategy;
    private PriceCalculationStrategy returnPriceCalculateStrategy;

    MoviePriceCalculationType(MovieType movieType, PriceCalculationStrategy priceCalculateStrategy,
                              PriceCalculationStrategy returnPriceCalculateStrategy) {
        this.movieType = movieType;
        this.priceCalculateStrategy = priceCalculateStrategy;
        this.returnPriceCalculateStrategy = returnPriceCalculateStrategy;
    }

    public static MoviePriceCalculationType resolveMoviePriceCalculationTypeByMovieType(MovieType movieType) {
        return Stream.of(MoviePriceCalculationType.values())
                .filter(priceType -> priceType.getMovieType().equals(movieType))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public PriceCalculationStrategy getPriceCalculationStrategy() {
        return priceCalculateStrategy;
    }

    public PriceCalculationStrategy getReturnPriceCalculateStrategy() {
        return returnPriceCalculateStrategy;
    }

    interface PriceCalculationStrategy {
        PriceCalculationStrategy PREMIUM = ((priceValue, days) -> priceValue.multiply(new BigDecimal(days)));
        PriceCalculationStrategy RETURN = (PriceCalculationStrategy::getReturnStrategy);

        static BigDecimal getReturnStrategy(BigDecimal priceValue, long days) {
            return days <= 0 ? BigDecimal.ZERO : priceValue.multiply(new BigDecimal(days));
        }

        BigDecimal calculate(BigDecimal priceValue, long days);
    }

    static class BasicPriceCalculateStrategy implements PriceCalculationStrategy {

        private int discountDays;

        BasicPriceCalculateStrategy(int discountDays) {
            this.discountDays = discountDays;
        }

        @Override
        public BigDecimal calculate(BigDecimal priceValue, long days) {
            if (days <= discountDays) {
                return priceValue;
            }
            long daysLeft = days - discountDays;
            return priceValue.add(priceValue.multiply(new BigDecimal(daysLeft)));
        }
    }

}
