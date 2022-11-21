package pl.dpotyralski.javaintegrationtestintroduction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.dpotyralski.javaintegrationtestintroduction.customer.Customer;
import pl.dpotyralski.javaintegrationtestintroduction.customer.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class _3_CustomerRepositoryJPAIntegrationTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void shouldSaveCustomerAndGetItBack() {
        //given
        Customer customer = new Customer("Tyrion");

        //when
        Customer saved = customerRepository.save(customer);

        //then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getUsername()).isEqualTo("Tyrion");
    }

}
