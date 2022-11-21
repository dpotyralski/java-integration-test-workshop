package pl.dpotyralski.javaintegrationtestintroduction.customer;

import lombok.AllArgsConstructor;
import pl.dpotyralski.javaintegrationtestintroduction.customer.exception.CustomerBonusPointsNotFound;
import pl.dpotyralski.javaintegrationtestintroduction.customer.exception.CustomerNotFound;

@AllArgsConstructor
public class CustomerSearch {

    private final CustomerRepository customerRepository;

    Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(CustomerNotFound::new);
    }

    int getBonusPointsByCustomerId(Long id) {
        return customerRepository.findBonusPointsByCustomerId(id)
                .orElseThrow(CustomerBonusPointsNotFound::new);
    }

}
