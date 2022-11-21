package pl.dpotyralski.javaintegrationtestintroduction;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.util.ReflectionUtils;
import pl.dpotyralski.javaintegrationtestintroduction.customer.Customer;
import pl.dpotyralski.javaintegrationtestintroduction.customer.CustomerRepository;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@TestConfiguration
class TestProfileConfiguration {

    @Bean
    @Profile("error")
    @Primary
    public CustomerRepository errorCustomerRepository() {
        return new CustomerRepository() {
            @Override
            public Optional<Customer> findById(Long id) {
                return Optional.empty();
            }

            @Override
            public Optional<Integer> findBonusPointsByCustomerId(Long id) {
                return Optional.empty();
            }

            @Override
            public Customer save(Customer customer) {
                throw new IllegalArgumentException();
            }
        };
    }

    @Bean
    @Primary
    @Profile("test")
    public CustomerRepository testCustomerRepository() {
        return new CustomerRepository() {
            ConcurrentHashMap<Long, Customer> customerMap = new ConcurrentHashMap<>();

            private AtomicLong counter = new AtomicLong();

            @Override
            public Optional<Customer> findById(Long id) {
                return Optional.ofNullable(customerMap.get(id));
            }

            @Override
            public Optional<Integer> findBonusPointsByCustomerId(Long id) {
                return Optional.of(customerMap.get(id).getBonusPoints());
            }

            @Override
            public Customer save(Customer customer) {

                customerMap.put(customer.getId(), customer);
                return customer;
            }
        };
    }


}
