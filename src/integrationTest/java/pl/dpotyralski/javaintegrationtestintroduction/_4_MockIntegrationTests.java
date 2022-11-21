package pl.dpotyralski.javaintegrationtestintroduction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import pl.dpotyralski.javaintegrationtestintroduction.customer.CustomerController;
import pl.dpotyralski.javaintegrationtestintroduction.customer.CustomerFacade;

import static org.mockito.Mockito.when;

@SpringBootTest
class _4_MockIntegrationTests {

    @MockBean
    CustomerFacade customerFacade;

    @Autowired
    CustomerController customerController;

    @Test
    void shouldGetPointsFromMockedBean() {
        //given
        when(customerFacade.getCustomerBonusPoints(100L)).thenReturn(50);

        //when
        Assertions.assertEquals(50, customerController.getCustomerBonusPoints(100L).getPoints());
    }

}
