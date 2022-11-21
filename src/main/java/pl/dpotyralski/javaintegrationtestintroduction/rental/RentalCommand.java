package pl.dpotyralski.javaintegrationtestintroduction.rental;

import lombok.Value;

import java.util.List;

@Value
class RentalCommand {

    Long customerId;
    List<RentalDetail> movieDetails;

}
