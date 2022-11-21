package pl.dpotyralski.javaintegrationtestintroduction.rental.protocol.request;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RentalPriceCalculateRequest {

    private Set<MovieDetailsRequest> details = new HashSet<>();

}
