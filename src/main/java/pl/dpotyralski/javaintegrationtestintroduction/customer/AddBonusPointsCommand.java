package pl.dpotyralski.javaintegrationtestintroduction.customer;

import lombok.Value;

@Value
public class AddBonusPointsCommand {

    private int bonusPoints;
    private long customerId;

}
