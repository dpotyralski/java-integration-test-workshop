package pl.dpotyralski.javaintegrationtestintroduction.rental.protocol.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReturnRequest {

    @NotNull
    private Long customerId;

    @NotNull
    private Long movieId;

}
