package pl.dpotyralski.javaintegrationtestintroduction.rental;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pl.dpotyralski.javaintegrationtestintroduction.customer.AddBonusPointsCommand;
import pl.dpotyralski.javaintegrationtestintroduction.customer.CustomerFacade;
import pl.dpotyralski.javaintegrationtestintroduction.movie.MovieType;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@AllArgsConstructor
public class RentalFacade {

    private final RentalSearcher rentalSearch;
    private final RentalCreator rentalCreator;
    private final RentalReturner rentalReturner;
    private final CustomerFacade customerFacade;

    @Transactional(readOnly = true)
    public List<RentalDto> findRentalsByCustomerId(Long customerId, boolean onlyActive) {
        return rentalSearch.findRentalsByCustomerIdAndRentalStatus(customerId, onlyActive).stream()
                .map(Rental::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public BigDecimal calculateRentalPrice(RentalPriceCalculateCommand rentalPriceCalculateCommand) {
        return rentalCreator.calculateRentalPrice(rentalPriceCalculateCommand);
    }

    public BigDecimal rent(RentalCommand rentalCommand) {
        List<RentalDto> rentals = rentalCreator.rent(rentalCommand);
        customerFacade.addBonusPointsToCustomer(new AddBonusPointsCommand(getBonusPoints(rentals), rentalCommand.getCustomerId()));
        return rentals.stream()
                .map(RentalDto::getPriceCalculated)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public RentalDto returnMovie(ReturnCommand returnCommand) {
        return rentalReturner.returnMovie(returnCommand).toDto();
    }

    private int getBonusPoints(List<RentalDto> rentals) {
        return rentals.stream()
                .map(RentalDto::getMovieType)
                .mapToInt(MovieType::getBonusPoints).sum();
    }

}
