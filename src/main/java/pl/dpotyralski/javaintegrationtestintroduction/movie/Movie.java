package pl.dpotyralski.javaintegrationtestintroduction.movie;

import lombok.Getter;
import pl.dpotyralski.javaintegrationtestintroduction.infrastructure.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "movies")
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private MovieType type;

    protected Movie() {
    }

    public Movie(String title, MovieType type) {
        this.title = title;
        this.type = type;
    }

    public MovieDto toDto() {
        return MovieDto.builder()
                .id(id)
                .title(title)
                .movieType(type)
                .build();
    }
}
