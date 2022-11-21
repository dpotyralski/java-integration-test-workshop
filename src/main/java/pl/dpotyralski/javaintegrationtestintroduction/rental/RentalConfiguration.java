package pl.dpotyralski.javaintegrationtestintroduction.rental;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.dpotyralski.javaintegrationtestintroduction.customer.CustomerFacade;
import pl.dpotyralski.javaintegrationtestintroduction.movie.MovieFacade;
import pl.dpotyralski.javaintegrationtestintroduction.infrastructure.TimeProvider;

@Configuration
class RentalConfiguration {

    @Bean
    public RentalPriceCalculator rentalPriceCalculator(RentalProperties rentalProperties) {
        return new RentalPriceCalculator(rentalProperties);
    }

    @Bean
    public RentalCreator rentalCreator(MovieFacade movieFacade, RentalPriceCalculator rentalPriceCalculator, RentalRepository rentalRepository, TimeProvider timeProvider) {
        return new RentalCreator(movieFacade, rentalPriceCalculator, rentalRepository, timeProvider);
    }

    @Bean
    public RentalSearcher rentalSearch(RentalRepository rentalRepository) {
        return new RentalSearcher(rentalRepository);
    }

    @Bean
    public RentalReturner rentalReturner(RentalRepository rentalRepository, RentalPriceCalculator rentalPriceCalculator, TimeProvider timeProvider) {
        return new RentalReturner(rentalRepository, rentalPriceCalculator, timeProvider);
    }

    @Bean
    RentalFacade rentalFacade(RentalSearcher rentalSearch, RentalCreator rentalCreator, RentalReturner rentalReturner, CustomerFacade customerFacade) {
        return new RentalFacade(rentalSearch, rentalCreator, rentalReturner, customerFacade);
    }

}
