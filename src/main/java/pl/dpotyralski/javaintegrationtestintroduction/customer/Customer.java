package pl.dpotyralski.javaintegrationtestintroduction.customer;

import lombok.Getter;
import pl.dpotyralski.javaintegrationtestintroduction.infrastructure.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private int bonusPoints = 0;

    protected Customer() {
    }

    public Customer(String username) {
        this.username = username;
    }

    public Customer(String username, int bonusPoints) {
        this.username = username;
        this.bonusPoints = bonusPoints;
    }

    CustomerDto toDto() {
        return CustomerDto.builder()
                .id(id)
                .username(username)
                .bonusPoints(bonusPoints)
                .build();
    }

    void addBonusPoints(int bonusPoints) {
        this.bonusPoints += bonusPoints;
    }
}
