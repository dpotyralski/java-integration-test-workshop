package pl.dpotyralski.javaintegrationtestintroduction.customer;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AllArgsConstructor
public class CustomerFacade {

    private final CustomerSearch customerSearch;
    private final CustomerCreator customerCreator;

    @Transactional(readOnly = true)
    public CustomerDto getCustomer(Long id) {
        return customerSearch.getCustomerById(id)
                .toDto();
    }

    @Transactional(readOnly = true)
    public int getCustomerBonusPoints(Long id) {
        return customerSearch.getBonusPointsByCustomerId(id);
    }

    public void addBonusPointsToCustomer(AddBonusPointsCommand addBonusPointsCommand) {
        customerSearch.getCustomerById(addBonusPointsCommand.getCustomerId())
                .addBonusPoints(addBonusPointsCommand.getBonusPoints());
    }

    public CustomerDto saveCustomer(CreateCustomerCommand createCustomerCommand) {
        return customerCreator.createCustomer(createCustomerCommand).toDto();
    }

}
