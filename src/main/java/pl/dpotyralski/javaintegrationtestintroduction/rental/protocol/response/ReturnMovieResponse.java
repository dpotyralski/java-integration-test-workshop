package pl.dpotyralski.javaintegrationtestintroduction.rental.protocol.response;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ReturnMovieResponse {

    BigDecimal lateReturnCharge;

}
