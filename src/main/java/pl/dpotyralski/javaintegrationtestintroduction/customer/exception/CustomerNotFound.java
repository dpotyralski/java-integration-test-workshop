package pl.dpotyralski.javaintegrationtestintroduction.customer.exception;

import pl.dpotyralski.javaintegrationtestintroduction.infrastructure.exceptions.ApplicationException;
import pl.dpotyralski.javaintegrationtestintroduction.infrastructure.exceptions.ErrorCodes;

public class CustomerNotFound extends ApplicationException {

    public CustomerNotFound() {
        super(ErrorCodes.CUSTOMER_NOT_FOUND_CODE, "Customer could not be found!");
    }

}
