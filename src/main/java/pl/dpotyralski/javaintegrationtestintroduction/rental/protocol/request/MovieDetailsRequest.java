package pl.dpotyralski.javaintegrationtestintroduction.rental.protocol.request;

import lombok.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
public class MovieDetailsRequest {

    @NotNull
    Long movieId;

    @Min(1)
    int days;
}
