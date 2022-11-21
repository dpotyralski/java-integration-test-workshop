package pl.dpotyralski.javaintegrationtestintroduction.customer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class CustomerCreator {

    private final CustomerRepository customerRepository;

    Customer createCustomer(CreateCustomerCommand createCustomerCommand) {
        Customer customer = new Customer(createCustomerCommand.getUsername());
        return customerRepository.save(customer);
    }

}
