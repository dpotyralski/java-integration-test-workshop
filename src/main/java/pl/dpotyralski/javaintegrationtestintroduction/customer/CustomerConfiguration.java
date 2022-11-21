package pl.dpotyralski.javaintegrationtestintroduction.customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
class CustomerConfiguration {

    @Bean
    @Profile("external")
    CustomerRepository customerRepository(RestTemplate restTemplate, @Value("${externals.catalogBaseUrl}") String baseUrl) {
        return new ExternalCustomerRepository(restTemplate, baseUrl);
    }

    @Bean
    CustomerRepository customerRepository(CustomerRepositoryJPA customerRepositoryJPA) {
        return customerRepositoryJPA;
    }

    @Bean
    public CustomerSearch customerSearch(CustomerRepository customerRepository) {
        return new CustomerSearch(customerRepository);
    }

    @Bean
    public CustomerCreator customerCreator(CustomerRepository customerRepository) {
        return new CustomerCreator(customerRepository);
    }

    @Bean
    public CustomerFacade customerFacade(CustomerCreator customerCreator, CustomerSearch customerSearch) {
        return new CustomerFacade(customerSearch, customerCreator);
    }

}
