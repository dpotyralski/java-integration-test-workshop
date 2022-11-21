package pl.dpotyralski.javaintegrationtestintroduction.rental;

import lombok.Value;

@Value
class RentalDetail {
    Long movieId;
    long days;
}