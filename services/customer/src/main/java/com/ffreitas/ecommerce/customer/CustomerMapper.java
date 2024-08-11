package com.ffreitas.ecommerce.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        if (request == null)
            return null;

        return Customer
                .builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(toAddress(request.address()))
                .build();
    }

    private Address toAddress(CustomerRequest.AddressDto addressDto) {
        if (addressDto == null)
            return null;

        return Address
                .builder()
                .street(addressDto.street())
                .houseNumber(addressDto.houseNumber())
                .zipCode(addressDto.zipCode())
                .build();
    }

    public ResponseCustomer toResponseCustomer(Customer customer) {
        return ResponseCustomer
                .builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(toResponseAddress(customer.getAddress()))
                .build();
    }

    private ResponseCustomer.ResponseAddress toResponseAddress(Address address) {
        if (address == null)
            return null;

        return ResponseCustomer.ResponseAddress
                .builder()
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .build();
    }
}
