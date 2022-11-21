package pl.dpotyralski.javaintegrationtestintroduction.infrastructure;

import lombok.AllArgsConstructor;

import java.time.Clock;
import java.time.LocalDate;

@AllArgsConstructor
public class TimeProvider {

    private final Clock clock;

    public LocalDate now() {
        return LocalDate.now(clock);
    }

}
