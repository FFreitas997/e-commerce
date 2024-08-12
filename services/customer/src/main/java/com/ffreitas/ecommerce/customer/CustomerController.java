package com.ffreitas.ecommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomer(@RequestBody @Valid CustomerRequest request) {
        log.info("Creating customer: {} {}", request.firstName(), request.lastName());
        return customerService.createCustomer(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCustomer(@RequestBody @Valid CustomerRequest request) {
        log.info("Updating customer with ID {}: {} {}", request.id(), request.firstName(), request.lastName());
        customerService.updateCustomer(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseCustomer> getAllCustomers() {
        log.info("Getting all customers");
        return customerService.getAllCustomers();
    }

    @GetMapping("/exists/{customer-id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean existsCustomerByID(@PathVariable("customer-id") String id) {
        log.info("Checking if customer with ID {} exists", id);
        return customerService.existsByID(id);
    }

    @GetMapping("/{customer-id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseCustomer getCustomerByID(@PathVariable("customer-id") String id) {
        log.info("Getting customer with ID {}", id);
        return customerService.getCustomerByID(id);
    }

    @DeleteMapping("/{customer-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCustomerByID(@PathVariable("customer-id") String id) {
        log.info("Deleting customer with ID {}", id);
        customerService.deleteCustomerByID(id);
    }
}
