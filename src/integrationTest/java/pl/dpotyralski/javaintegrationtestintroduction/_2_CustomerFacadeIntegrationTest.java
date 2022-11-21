package pl.dpotyralski.javaintegrationtestintroduction;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.dpotyralski.javaintegrationtestintroduction.customer.AddBonusPointsCommand;
import pl.dpotyralski.javaintegrationtestintroduction.customer.CreateCustomerCommand;
import pl.dpotyralski.javaintegrationtestintroduction.customer.CustomerDto;
import pl.dpotyralski.javaintegrationtestintroduction.customer.CustomerFacade;

@SpringBootTest
@ActiveProfiles("test")
//@ContextConfiguration(classes = TestProfileConfiguration.class)
class _2_CustomerFacadeIntegrationTest {

    @Autowired
    CustomerFacade customerFacade;

    @Test
    void shouldGetZeroPointsForNewlyCreatedCustomer() {
        //given
        CreateCustomerCommand newCustomer = new CreateCustomerCommand("Tyrion");

//        when
        CustomerDto actualCustomer = customerFacade.saveCustomer(newCustomer);

        //then
        Assertions.assertThat(customerFacade.getCustomerBonusPoints(1L)).isZero();
    }

    @Test
    void shouldAddPointsForNewlyCreatedCustomer() {
        //given
        CreateCustomerCommand newCustomer = new CreateCustomerCommand("Aria");
        CustomerDto actualCustomer = customerFacade.saveCustomer(newCustomer);

        //when
        customerFacade.addBonusPointsToCustomer(new AddBonusPointsCommand(20, actualCustomer.getId()));

        //then
        Assertions.assertThat(customerFacade.getCustomerBonusPoints(actualCustomer.getId())).isEqualTo(20);
    }

}
