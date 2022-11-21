package pl.dpotyralski.javaintegrationtestintroduction.rental;

import lombok.AllArgsConstructor;
import pl.dpotyralski.javaintegrationtestintroduction.infrastructure.TimeProvider;
import pl.dpotyralski.javaintegrationtestintroduction.rental.exception.RentalNotFound;

@AllArgsConstructor
class RentalReturner {

    private final RentalRepository rentalRepository;
    private final RentalPriceCalculator rentalPriceCalculator;
    private final TimeProvider timeProvider;

    Rental returnMovie(ReturnCommand returnCommand) {
        Rental currentRental = rentalRepository.findById(returnCommand.getRentalId())
                .orElseThrow(() -> new RentalNotFound(returnCommand.getRentalId()));

        currentRental.returnMovie(rentalPriceCalculator, timeProvider.now());
        return currentRental;
    }

}
