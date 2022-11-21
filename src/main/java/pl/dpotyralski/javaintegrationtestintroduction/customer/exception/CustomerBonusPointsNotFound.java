package pl.dpotyralski.javaintegrationtestintroduction.customer.exception;

import pl.dpotyralski.javaintegrationtestintroduction.infrastructure.exceptions.ApplicationException;
import pl.dpotyralski.javaintegrationtestintroduction.infrastructure.exceptions.ErrorCodes;

public class CustomerBonusPointsNotFound extends ApplicationException {

    public CustomerBonusPointsNotFound() {
        super(ErrorCodes.CUSTOMER_BONUS_POINTS_NOT_FOUND_CODE, "Customer bonus points could not be found!");
    }

}
