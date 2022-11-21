package pl.dpotyralski.javaintegrationtestintroduction.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MovieType {

    NEW_RELEASE(PriceType.PREMIUM, 2),
    REGULAR(PriceType.BASIC, 1),
    OLD(PriceType.BASIC, 1);

    private PriceType priceType;
    private int bonusPoints;

    public int getBonusPoints() {
        return bonusPoints;
    }
}

