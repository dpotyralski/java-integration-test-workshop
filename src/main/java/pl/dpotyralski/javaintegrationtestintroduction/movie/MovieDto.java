package pl.dpotyralski.javaintegrationtestintroduction.movie;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieDto {

    private Long id;
    private String title;
    private MovieType movieType;

}
