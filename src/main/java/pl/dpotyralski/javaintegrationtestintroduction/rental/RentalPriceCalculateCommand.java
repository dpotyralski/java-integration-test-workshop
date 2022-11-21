package pl.dpotyralski.javaintegrationtestintroduction.rental;

import lombok.Value;

import java.util.List;

@Value
class RentalPriceCalculateCommand {

    private List<RentalDetail> details;

}
