package com.ffreitas.ecommerce.customer;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = mapper.toCustomer(request);
        var saved = repository.save(customer);
        return saved.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var entity = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException("No such customer with ID: " + request.id()));

        BiFunction<CustomerRequest.AddressDto, Address, Address> updateAddress = (CustomerRequest.AddressDto addressDto, Address address) -> {

            address.setStreet(StringUtils.isNotBlank(addressDto.street()) ? addressDto.street() : address.getStreet());

            address.setHouseNumber(StringUtils.isNotBlank(addressDto.houseNumber()) ? addressDto.houseNumber() : address.getHouseNumber());

            address.setZipCode(StringUtils.isNotBlank(addressDto.zipCode()) ? addressDto.zipCode() : address.getZipCode());

            return address;
        };

        entity.setFirstName(StringUtils.isNotBlank(request.firstName()) ? request.firstName() : entity.getFirstName());

        entity.setLastName(StringUtils.isNotBlank(request.lastName()) ? request.lastName() : entity.getLastName());

        entity.setEmail(StringUtils.isNotBlank(request.email()) ? request.email() : entity.getEmail());

        entity.setAddress(request.address() != null ? updateAddress.apply(request.address(), entity.getAddress()) : entity.getAddress());
    }

    public List<ResponseCustomer> getAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseCustomer)
                .toList();
    }

    public Boolean existsByID(String id) {
        return repository.existsById(id);
    }

    public ResponseCustomer getCustomerByID(String id) {
        return repository.findById(id)
                .map(mapper::toResponseCustomer)
                .orElseThrow(() -> new CustomerNotFoundException("No such customer with ID: " + id));
    }

    public void deleteCustomerByID(String id) {
        repository.deleteById(id);
    }
}
