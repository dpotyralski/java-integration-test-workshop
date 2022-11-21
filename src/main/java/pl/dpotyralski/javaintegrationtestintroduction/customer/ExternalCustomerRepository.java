package pl.dpotyralski.javaintegrationtestintroduction.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
class ExternalCustomerRepository implements CustomerRepository {

    private static final String CUSTOMERS_PATH = "customers";

    private final RestTemplate restTemplate;
    private final String baseMovieCatalogUrl;

    @Override
    public Optional<Customer> findById(Long id) {
        String uri = UriComponentsBuilder.fromHttpUrl(baseMovieCatalogUrl)
                .path(CUSTOMERS_PATH)
                .pathSegment(String.valueOf(id))
                .build()
                .toUriString();

        try {
            return Optional.ofNullable(restTemplate.getForObject(uri, Customer.class));
        } catch (RestClientException e) {
            log.error("Error while fetching movie", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Integer> findBonusPointsByCustomerId(Long id) {
        return Optional.empty();
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }
}
