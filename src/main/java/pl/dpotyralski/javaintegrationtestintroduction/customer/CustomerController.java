package pl.dpotyralski.javaintegrationtestintroduction.customer;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.dpotyralski.javaintegrationtestintroduction.customer.protocol.BonusPointsResponse;
import pl.dpotyralski.javaintegrationtestintroduction.customer.protocol.CustomerRequest;
import pl.dpotyralski.javaintegrationtestintroduction.customer.protocol.CustomerResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private CustomerFacade customerFacade;

    @PostMapping
    @ApiOperation(value = "Customer creation")
    public CustomerResponse storeConsumer(@RequestBody @Valid CustomerRequest customerRequest) {
        CustomerDto customer = customerFacade.saveCustomer(new CreateCustomerCommand(customerRequest.getUsername()));
        return new CustomerResponse(customer.getId(), customer.getUsername());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Customer bonus points")
    public CustomerResponse getCustomerById(@PathVariable Long id) {
        log.info("Getting customer with id: " + id);
        CustomerDto customer = customerFacade.getCustomer(id);
        return new CustomerResponse(customer.getId(), customer.getUsername());
    }

    @GetMapping("/{id}/bonus-points")
    @ApiOperation(value = "Customer bonus points")
    public BonusPointsResponse getCustomerBonusPoints(@PathVariable Long id) {
        return new BonusPointsResponse(customerFacade.getCustomerBonusPoints(id));
    }

}
