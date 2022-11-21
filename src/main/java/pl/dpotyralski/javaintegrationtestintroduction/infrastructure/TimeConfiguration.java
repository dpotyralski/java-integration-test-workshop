package pl.dpotyralski.javaintegrationtestintroduction.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
class TimeConfiguration {

    @Bean
    public TimeProvider timeProvider(){
        return new TimeProvider(Clock.systemDefaultZone());
    }

}
