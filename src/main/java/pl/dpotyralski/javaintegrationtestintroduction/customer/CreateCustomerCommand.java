package pl.dpotyralski.javaintegrationtestintroduction.customer;

import lombok.Value;

@Value
public class CreateCustomerCommand {
    private String username;
}
