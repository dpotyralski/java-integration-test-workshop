package pl.dpotyralski.javaintegrationtestintroduction.customer;

import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findById(Long id);

    Optional<Integer> findBonusPointsByCustomerId(Long id);

    Customer save(Customer customer);


}
