package pl.dpotyralski.javaintegrationtestintroduction.rental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static pl.dpotyralski.javaintegrationtestintroduction.rental.MoviePriceCalculationType.BasicPriceCalculateStrategy;

class MoviePriceCalculationTypeTest {

    @ParameterizedTest
    @CsvSource(value = {"2,5,120", "5, 5, 120", "4, 2, 360"})
    void shouldNotGetAnyDiscount(int rentalDays, int discountDays, BigDecimal expectedPrice) {
        //given
        BasicPriceCalculateStrategy basicPriceCalculateStrategy = new BasicPriceCalculateStrategy(discountDays);

        //when
        BigDecimal actualPrice = basicPriceCalculateStrategy.calculate(new BigDecimal("120"), rentalDays);

        //then
        Assertions.assertEquals(expectedPrice, actualPrice);
    }
}