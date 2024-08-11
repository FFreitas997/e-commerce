package com.ffreitas.ecommerce.customer;

import lombok.Builder;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */

@Builder
public record ResponseCustomer(

        String id,

        String firstName,

        String lastName,

        String email,

        ResponseAddress address

) implements Serializable {

    /**
     * DTO for {@link Address}
     */

    @Builder
    public record ResponseAddress(

            String street,

            String houseNumber,

            String zipCode

    ) implements Serializable {
    }
}