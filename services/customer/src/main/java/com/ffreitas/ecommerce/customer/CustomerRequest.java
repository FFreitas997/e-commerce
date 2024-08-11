package com.ffreitas.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */

public record CustomerRequest(

        String id,

        @NotNull(message = "Customer first name is required")
        String firstName,

        @NotNull(message = "Customer last name is required")
        String lastName,

        @NotNull(message = "Customer email is required")
        @Email(message = "Customer email is invalid")
        String email,

        AddressDto address

) implements Serializable {

    /**
     * DTO for {@link Address}
     */

    public record AddressDto(

            String street,

            String houseNumber,

            String zipCode

    ) implements Serializable {
    }
}